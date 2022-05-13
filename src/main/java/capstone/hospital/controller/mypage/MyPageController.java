package capstone.hospital.controller.mypage;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.mypage.form.DeleteForm;
import capstone.hospital.controller.mypage.form.PasswordForm;
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
    public String changePw(@Login Object loginMember, @ModelAttribute("form") PasswordForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/mypage/changePw";
    }

    @PostMapping("/changePw")
    public String changePw(@Login Object loginMember, @ModelAttribute("form") PasswordForm form) {
        Patient patient = (Patient) loginMember;
        basicService.changePw(patient.getId(), form.getNowPw(), form.getNewPw());
        return "redirect:/logout";
    }

    @GetMapping("/delete")
    public String delete(@Login Object loginMember, @ModelAttribute("pw") String password, Model model) {
        model.addAttribute("loginMember", loginMember);

        return "/mypage/delete";
    }

    @PostMapping("/delete")
    public String delete(@Login Object loginMember, @ModelAttribute("pw") String password) {
        Patient patient = (Patient) loginMember;
        log.info("password={}", password);
        basicService.delete(patient.getId());
        return "redirect:/logout";
    }
}
