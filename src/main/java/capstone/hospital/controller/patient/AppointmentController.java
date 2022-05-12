package capstone.hospital.controller.patient;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.patient.form.MajorForm;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class AppointmentController {

    private final DoctorRepository doctorRepository;

    @ModelAttribute("majors")
    public Major[] majorTypes() {
        return Major.values();
    }

    @GetMapping("/appointment")
    public String appointment1(@Login Object loginMember, @ModelAttribute("form") MajorForm major, Model model) {
        model.addAttribute("loginMember", loginMember);

        return "/patient/appointment1";
    }

    @GetMapping("/appointment/{major}")
    public String appointment2(@Login Object loginMember, @PathVariable Major major, Model model) {
        model.addAttribute("loginMember", loginMember);

        List<Doctor> doctors = doctorRepository.findByMajor(major);
        model.addAttribute("doctors", doctors);

        return "/patient/appointment2";
    }
}
