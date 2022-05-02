package capstone.hospital.controller.join;

import capstone.hospital.controller.join.form.CheckForm;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.form.JoinPatientForm;
import capstone.hospital.service.JoinService;
import capstone.hospital.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/join/patient")
public class JoinPatientController {

    private final JoinService joinService;
    private final ValidateService validateService;

    /**
     * 환자 회원 가입
     */
    @GetMapping
    public String agreement(){
        return "join/patient/termsAndConditions";
    }

    @PostMapping
    public String agreementCheck(){
        return "redirect:/join/patient/phoneCheck";
    }

    @GetMapping("/phoneCheck")
    public String validate(@ModelAttribute("phone") CheckForm form) {
        return "join/patient/phoneCheck";
    }

    @PostMapping("/phoneCheck")
    public String sendSms(@Validated @ModelAttribute("phone") CheckForm form, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        log.info("form={}", form);
        if (form.getPhoneNumber().isEmpty()) {
            bindingResult.reject("nullFail", "핸드폰 번호를 입력해주세요.");
            return "join/patient/phoneCheck";
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("checkForm") == null) {
            log.info("인증번호 발송");
//            String checkNumber = validateService.sendSMS(phoneNumber);
            String checkNumber = "123456";
            CheckForm checkForm = new CheckForm(form.getName(), form.getPhoneNumber(), form.getRrnFront(), form.getRrnBack(), checkNumber);
            session.setAttribute("checkForm", checkForm);
            session.setMaxInactiveInterval(3 * 60);
            return "join/patient/phoneCheck";
        } else {
            CheckForm checkForm = (CheckForm) session.getAttribute("checkForm");
            if (checkForm.getCheckNumber().equals(form.getInputNumber())) {
                log.info("인증 성공");
                // 중복 검사
                boolean check = joinService.validateDuplicateRRN(checkForm.getRrn());
                if (!check) {
                    return "/join/joinFail";
                }
                redirectAttributes.addFlashAttribute("name", checkForm.getName());
                redirectAttributes.addFlashAttribute("phone", checkForm.getPhoneNumber());
                redirectAttributes.addFlashAttribute("rrnFront", checkForm.getRrnFront());
                log.info("session={}", session.getAttribute("checkForm"));
                session.invalidate();
                return "redirect:/join/patient/joinForm";
            } else {
                log.info("인증 실패");
                bindingResult.reject("checkFail", "인증 번호가 맞지 않습니다.");
                return "join/patient/phoneCheck";
            }
        }
    }

    @GetMapping("/joinForm")
    public String joinPatient(@ModelAttribute("patient") JoinPatientForm form, Model model) {
        form.setName((String) model.asMap().get("name"));
        form.setPhoneNumber((String) model.asMap().get("phone"));
        form.setRrnFront((String) model.asMap().get("rrnFront"));
        return "join/patient/userJoinPatient";
    }

    @PostMapping("/joinForm")
    public String savePatient(@Validated @ModelAttribute("patient") JoinPatientForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        log.info("input={}", form);

        // 아이디 중복 체크
        boolean idCheck = joinService.validateDuplicateLoginId(form.getLoginId());
        if (!idCheck) {
            log.info("idCheckFail");
            bindingResult.reject("idCheckFail", "이미 사용중인 아이디입니다.");
            return "join/patient/userJoinPatient";
        }

        if (bindingResult.hasErrors()) {
            return "join/patient/userJoinPatient";
        }

        // 비밀번호 확인
        if (!form.getLoginPw().equals(form.getCheckPw())) {
            bindingResult.reject("pwCheckFail", "입력된 비밀번호가 다릅니다.");
            return "join/patient/userJoinPatient";
        }

        // 회원 중복 체크
        boolean rrnCheck = joinService.validateDuplicateRRN(form.getRrnFront() + "-" + form.getRrnBack());
        if (!rrnCheck) {
            bindingResult.reject("rrnCheckFail", "이미 가입된 회원입니다.");
            return "join/patient/userJoinPatient";
        }

        // success
        patientSave(form);

        redirectAttributes.addFlashAttribute("name", form.getName());
        return "redirect:/join/success";
    }

    private void patientSave(JoinPatientForm form) {
        Address newAddress = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Information newInfo = new Information(form.getName(), form.getRrnFront(), form.getRrnBack(), form.getPhoneNumber(), newAddress);
        Patient patient = new Patient(form.getLoginId(), form.getLoginPw(), newInfo);
        Long savedId = joinService.joinPatient(patient);
    }


}
