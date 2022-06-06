package capstone.hospital.controller;

import capstone.hospital.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    @GetMapping("/use")
    public String use(@Login Object loginMember, Model model) {
        if (loginMember == null) {
            return "/main/use";
        }
        model.addAttribute("loginMember", loginMember);
        return "/main/useLogin";
    }

    @GetMapping("/info")
    public String info(@Login Object loginMember, Model model) {
        if (loginMember == null) {
            return "/main/info";
        }
        model.addAttribute("loginMember", loginMember);
        return "/main/infoLogin";
    }

    @GetMapping("/way")
    public String way(@Login Object loginMember, Model model) {
        if (loginMember == null) {
            return "/main/way";
        }
        model.addAttribute("loginMember", loginMember);
        return "/main/wayLogin";
    }
}
