package capstone.hospital.controller.mypage;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.mypage.form.ChangePwForm;
import capstone.hospital.controller.mypage.form.CheckPwForm;
import capstone.hospital.domain.Patient;
import capstone.hospital.service.BasicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MyPageController {

    private final BasicService basicService;

    @GetMapping
    public String myPage(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        Patient patient = (Patient) loginMember;
        return "/mypage/updateInfo";
    }

    @GetMapping("/changePw")
    public String changePw(@Login Object loginMember, @ModelAttribute("form") ChangePwForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/mypage/changePw";
    }

    @PostMapping("/changePw")
    public String changePw(@Login Object loginMember, @ModelAttribute("form") ChangePwForm form) {
        Patient patient = (Patient) loginMember;
        basicService.changePw(patient.getId(), form.getNowPw(), form.getNewPw());
        return "redirect:/logout";
    }

    @GetMapping("/delete")
    public String delete(@Login Object loginMember, @ModelAttribute("form") CheckPwForm password, Model model) {
        model.addAttribute("loginMember", loginMember);

        return "/mypage/delete";
    }

    @PostMapping("/delete")
    public String delete(@Login Object loginMember, @ModelAttribute("form") CheckPwForm form) {
        Patient patient = (Patient) loginMember;
        if (form.getPassword().equals(patient.getLoginPw())) {
            basicService.delete(patient.getId());
        } else {
            log.info("password not equal");
        }
        return "redirect:/logout";
    }
}
