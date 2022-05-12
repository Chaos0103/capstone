package capstone.hospital.controller.patient;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.patient.form.MajorForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class DepartmentSearchController {

//    @GetMapping("/departmentSearch")
    public String departmentSearch(@Login Object loginMember, @ModelAttribute("form") MajorForm major, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "patient/departmentSearch";
    }
}
