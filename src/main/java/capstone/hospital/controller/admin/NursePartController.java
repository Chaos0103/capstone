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
@RequestMapping("/admin/nurse")
public class NursePartController {

    private final AdminService adminService;

    @GetMapping("/list")
    public String approvedNurse(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("nurses", adminService.readNurse(form.getName()));
        return "/admin/nurse/list";
    }

    @GetMapping("/list/{nurseId}/delete")
    public String delete(@Login Object loginMember, @PathVariable("nurseId") Long nurseId, Model model) {
        model.addAttribute("loginMember", loginMember);
        adminService.deleteNurse(nurseId);
        return "redirect:/admin/nurse/list";
    }

    @GetMapping("/approve")
    public String approvalDoctor(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("nurses", adminService.waitNurse(form.getName()));
        return "admin/nurse/approve";
    }

    @GetMapping("/approve/{nurseId}")
    public String approve(@Login Object loginMember, @PathVariable Long nurseId, Model model) {
        model.addAttribute("loginMember", loginMember);
        adminService.approveNurse(nurseId, (Admin) loginMember);
        return "redirect:/admin/nurse/approve";
    }


}
