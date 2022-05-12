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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class SearchController {

    private final DoctorRepository doctorRepository;

    @ModelAttribute("majors")
    public Major[] majorTypes() {
        return Major.values();
    }

    @GetMapping("/doctorSearch")
    public String doctorSearch(@Login Object loginMember, @ModelAttribute("form") MajorForm major, Model model) {
        model.addAttribute("loginMember", loginMember);
        List<Doctor> doctors = new ArrayList<>();
        if (major.getMajor() == null) {
            doctors = doctorRepository.findAll();
        } else {
            doctors = doctorRepository.findByMajor(major.getMajor());
        }
        model.addAttribute("doctors", doctors);
        return "patient/doctorSearch";
    }

}
