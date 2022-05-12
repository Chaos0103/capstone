package capstone.hospital.controller.patient;

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
@RequestMapping("/patient")
public class AppointmentListController {

    @GetMapping("/appointmentList")
    public String appointments(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/patient/appointmentList";
    }
}
