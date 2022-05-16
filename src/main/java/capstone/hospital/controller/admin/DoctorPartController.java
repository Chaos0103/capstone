package capstone.hospital.controller.admin;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.admin.form.SearchForm;
import capstone.hospital.domain.Admin;
import capstone.hospital.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/doctor")
public class DoctorPartController {

    private final AdminService adminService;

    @GetMapping("/list")
    public String approvedDoctor(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("doctors", adminService.readDoctor(form.getName()));
        return "admin/doctor/list";
    }

    @GetMapping("/approve")
    public String approvalDoctor(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("doctors", adminService.waitDoctor(form.getName()));
        return "admin/doctor/approve";
    }

    @GetMapping("/approve/{doctorId}")
    public String approve(@Login Object loginMember, @PathVariable Long doctorId, Model model) {
        model.addAttribute("loginMember", loginMember);
        adminService.approveDoctor(doctorId, (Admin) loginMember);
        return "redirect:/admin/doctor/approve";
    }

//    @GetMapping("/list/{doctorId}/edit")
    public String edit(@Login Object loginMember, @PathVariable Long doctorId, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "admin/doctor/edit";
    }

    @GetMapping("/list/{doctorId}/delete")
    public String delete(@Login Object loginMember, @PathVariable Long doctorId, Model model) {
        model.addAttribute("loginMember", loginMember);
        adminService.deleteDoctor(doctorId);
        return "redirect:/admin/doctor/list";
    }

}
