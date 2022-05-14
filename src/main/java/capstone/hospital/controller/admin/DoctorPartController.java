package capstone.hospital.controller.admin;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.domain.Doctor;
import capstone.hospital.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
//@RequestMapping("/admin")
public class DoctorPartController {

    private final AdminService adminService;

//    @GetMapping
    public String approvedDoctor(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("doctors", adminService.readDoctor());
        return "admin/doctor/list";
    }

//    @GetMapping
    public String approvalDoctor(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("doctors", adminService.waitDoctor());
        return "admin/doctor/approval";
    }



}
