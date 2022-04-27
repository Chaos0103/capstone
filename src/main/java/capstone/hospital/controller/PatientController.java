package capstone.hospital.controller;

import capstone.hospital.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    @GetMapping
    public String patient(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "patient/patientHome";
    }

    @GetMapping("/MedicalView")
    public String medicalView(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "patient/patientMedicalView";
    }

    @GetMapping("/appointment")
    public String appointment(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "patient/patientAppointment";
    }

}
