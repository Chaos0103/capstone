package capstone.hospital.controller.login;

import capstone.hospital.exception.LoginException;
import capstone.hospital.controller.login.form.FindLoginIdForm;
import capstone.hospital.controller.login.form.FindLoginPwForm;
import capstone.hospital.controller.login.form.LoginForm;
import capstone.hospital.service.LoginService;
import capstone.hospital.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final ValidateService validateService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @Validated @ModelAttribute LoginForm form, BindingResult bindingResult,
            @RequestParam(defaultValue = "/") String redirectURL,
             HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Object loginMember = loginService.login(form.getLoginId(), form.getLoginPw());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        // login success
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);

        return "redirect:" + redirectURL;
    }

    /**
     * 아이디 찾기
     */
    @GetMapping("/login/findLoginId")
    public String findLoginId(@ModelAttribute("findLoginId") FindLoginIdForm form) {
        return "login/findLoginId";
    }

    @PostMapping("/login/findLoginId")
    public String findLoginIdCheck(@ModelAttribute("findLoginId") FindLoginIdForm form, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        HttpSession session = request.getSession();
        if (session.getAttribute("checkForm") == null) {
            log.info("인증번호 발송");
            String phoneNumber = form.getPhoneNumberFront() + form.getPhoneNumberMid() + form.getPhoneNumberBack();
//            String checkNumber = validateService.sendSMS(phoneNumber);
            String checkNumber = "123456";
            FindLoginIdForm checkForm = new FindLoginIdForm(form.getName(), form.getPhoneNumberFront(), form.getPhoneNumberMid(), form.getPhoneNumberBack(), checkNumber);
            session.setAttribute("checkForm", checkForm);
            session.setMaxInactiveInterval(3 * 60);
            return "login/findLoginId";
        } else {
            FindLoginIdForm checkForm = (FindLoginIdForm) session.getAttribute("checkForm");
            if (checkForm.getCheckNumber().equals(form.getInputNumber())) {
                log.info("인증 성공");
                redirectAttributes.addFlashAttribute("name", form.getName());
                redirectAttributes.addFlashAttribute("phoneNumber", form.getPhoneNumberFront() + form.getPhoneNumberMid() + form.getPhoneNumberBack());
                session.invalidate();
                return "redirect:/login/findId";
            } else {
                log.info("인증 실패");
                bindingResult.reject("checkFail", "인증 번호가 맞지 않습니다.");
                return "login/findLoginId";
            }
        }
    }

    @GetMapping("/login/findId")
    public String findId(@ModelAttribute("findLoginId") FindLoginIdForm form, Model model) {
        form.setName((String) model.asMap().get("name"));
        form.setPhoneNumber((String) model.asMap().get("phoneNumber"));
        try {
            String loginId = loginService.findLoginId(form.getName(), form.getPhoneNumber());
            model.addAttribute("title", "아이디 찾기");
            model.addAttribute("login", loginId);
        } catch (LoginException e) {
            model.addAttribute("login", e.getMessage());
        }

        return "login/find";
    }

    /**
     * 비밀번호 찾기
     */
    @GetMapping("/login/findLoginPw")
    public String findLoginPw(@ModelAttribute("findLoginPw") FindLoginPwForm form) {
        return "login/findLoginPw";
    }

    @PostMapping("/login/findLoginPw")
    public String findLoginPwCheck(@ModelAttribute("findLoginPw") FindLoginPwForm form, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        HttpSession session = request.getSession();
        if (session.getAttribute("checkForm") == null) {
            log.info("인증번호 발송");
            String phoneNumber = form.getPhoneNumberFront() + form.getPhoneNumberMid() + form.getPhoneNumberBack();
//            String checkNumber = validateService.sendSMS(phoneNumber);
            String checkNumber = "123456";
            FindLoginPwForm checkForm = new FindLoginPwForm(form.getName(), form.getPhoneNumberFront(), form.getPhoneNumberMid(), form.getPhoneNumberBack(), form.getLoginId(), checkNumber);
            session.setAttribute("checkForm", checkForm);
            session.setMaxInactiveInterval(3 * 60);
            return "login/findLoginPw";
        } else {
            FindLoginPwForm checkForm = (FindLoginPwForm) session.getAttribute("checkForm");
            if (checkForm.getCheckNumber().equals(form.getInputNumber())) {
                log.info("인증 성공");
                redirectAttributes.addFlashAttribute("name", form.getName());
                redirectAttributes.addFlashAttribute("phoneNumber", form.getPhoneNumberFront() + form.getPhoneNumberMid() + form.getPhoneNumberBack());
                redirectAttributes.addFlashAttribute("loginId", form.getLoginId());
                session.invalidate();
                return "redirect:/login/findPw";
            } else {
                log.info("인증 실패");
                bindingResult.reject("checkFail", "인증 번호가 맞지 않습니다.");
                return "login/findLoginPw";
            }
        }
    }

    @GetMapping("/login/findPw")
    public String findPw(@ModelAttribute("findLoginPw") FindLoginPwForm form, Model model) {
        form.setName((String) model.asMap().get("name"));
        form.setPhoneNumber((String) model.asMap().get("phoneNumber"));
        form.setLoginId((String) model.asMap().get("loginId"));
        try {
            String loginPw = loginService.findLoginPw(form.getName(), form.getPhoneNumber(), form.getLoginId());
            model.addAttribute("title", "비밀번호 찾기");
            model.addAttribute("login", loginPw);
        } catch (LoginException e) {
            model.addAttribute("login", e.getMessage());
        }
        return "login/find";
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
