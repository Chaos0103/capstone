package capstone.hospital.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    @GetMapping
    public String admin() {
        return "patient/patientHome";
    }

    @GetMapping("/MedicalView")
    public String medicalView() {
        return "patient/patientMedicalView";
    }

    @GetMapping("/appointment")
    public String appointment() {
        return "patient/patientAppointment";
    }

}
