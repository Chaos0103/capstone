package capstone.hospital.controller.mypage;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.mypage.form.ChangePwForm;
import capstone.hospital.controller.mypage.form.CheckPwForm;
import capstone.hospital.controller.mypage.form.InfoForm;
import capstone.hospital.dto.InfoDto;
import capstone.hospital.service.BasicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final BasicService basicService;

    @GetMapping
    public String myPage(@Login Object loginMember, @ModelAttribute("form") InfoForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        InfoDto info = basicService.findInfo(loginMember);
        form.setPhoneNumber(info.getPhoneNumber());
        form.setCity(info.getCity());
        form.setStreet(info.getStreet());
        form.setZipcode(info.getZipcode());
        form.setMemberType(info.getMemberType().getDescription());
        // 직원, 고객 리턴값 다르게 설정

        return "/mypage/updateInfo";
    }

    @PostMapping
    public String infoUpdate(@Login Object loginMember, @Validated @ModelAttribute("form") InfoForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        basicService.changeMemberInfo(loginMember, new InfoDto(form.getPhoneNumber(), form.getCity(), form.getStreet(), form.getZipcode()));
        return "redirect:/";
    }

    @GetMapping("/changePw")
    public String changePw(@Login Object loginMember, @ModelAttribute("form") ChangePwForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/mypage/changePw";
    }

    @PostMapping("/changePw")
    public String changePw(@Login Object loginMember, @Validated @ModelAttribute("form") ChangePwForm form) {
        try {
            basicService.changePw(loginMember, form.getNowPw(), form.getNewPw());
        } catch (IllegalStateException e) {
            log.info("error={}", e.getMessage());
        }
        return "redirect:/logout";
    }

    @GetMapping("/delete")
    public String delete(@Login Object loginMember, @ModelAttribute("form") CheckPwForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/mypage/delete";
    }

    @PostMapping("/delete")
    public String delete(@Login Object loginMember, @Validated @ModelAttribute("form") CheckPwForm form, BindingResult bindingResult, Model model) {
        try {
            basicService.delete(loginMember, form.getPassword());
            return "redirect:/logout";
        } catch (IllegalStateException e) {
            model.addAttribute("loginMember", loginMember);
            bindingResult.reject("fail", e.getMessage());
            return "/mypage/delete";
        }
    }
}
