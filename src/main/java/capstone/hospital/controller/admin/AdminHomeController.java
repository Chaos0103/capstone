package capstone.hospital.controller.admin;

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
@RequestMapping("/admin")
public class AdminHomeController {

    @GetMapping
    public String adminHome(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/admin/home";
    }
}
