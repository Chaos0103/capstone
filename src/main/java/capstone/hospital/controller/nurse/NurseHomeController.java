package capstone.hospital.controller.nurse;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.service.InpatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/nurse")
public class NurseHomeController {

    private final InpatientService inpatientService;

    @GetMapping
    public String home(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("today", LocalDateTime.now());
        model.addAttribute("totalInpatient", inpatientService.totalInpatient());
        model.addAttribute("todayInpatient", inpatientService.todayInpatient());
        return "/nurse/home";
    }
}
