package capstone.hospital.controller;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.KCDCode;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.KCDCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final DoctorRepository doctorRepository;
    private final KCDCodeRepository kcdCodeRepository;

    @GetMapping
    public String admin() {
        return "admin/adminHome";
    }

    @GetMapping("/doctorView")
    public String doctorView(Model model) {
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        return "admin/doctor/doctorView";
    }

    @GetMapping("/doctorView/{doctorId}")
    public String doctorInfo(@PathVariable Long doctorId, Model model) {
        Doctor doctor = doctorRepository.findById(doctorId).get();
        model.addAttribute("doctor", doctor);
        return "admin/doctor/doctorInfo";
    }

    @GetMapping("/diseaseView")
    public String diseaseView(Model model) {
        List<KCDCode> codes = kcdCodeRepository.findAll();
        model.addAttribute("codes", codes);
        return "admin/disease/diseaseView";
    }
}
