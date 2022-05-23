package capstone.hospital.controller.admin;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.admin.form.EslForm;
import capstone.hospital.controller.admin.form.SearchForm;
import capstone.hospital.dto.DoctorEslDto;
import capstone.hospital.dto.DoctorInfoDto;
import capstone.hospital.service.DoctorEslService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/esl")
public class EslPartController {

    private final DoctorEslService service;

    @GetMapping
    public String list(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("list", service.list(form.getName()));
        return "/admin/esl/list";
    }

    @GetMapping("/create")
    public String create(@Login Object loginMember, @ModelAttribute("form") EslForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/admin/esl/create";
    }

    @PostMapping("/create")
    public String save(@Login Object loginMember, @ModelAttribute("form") EslForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        if (form.getMajor().isEmpty()) {
            DoctorInfoDto data = service.findDoctor(form.getName());
            form.input(data);
            return "/admin/esl/create";
        }
        service.save(form.getName(), form.getRoom());
        return "redirect:/admin/esl";
    }

    @GetMapping("/{eslId}/delete")
    public String delete(@PathVariable("eslId") Long id) {
        service.delete(id);
        return "redirect:/admin/esl";
    }

    @GetMapping("/{eslId}/edit")
    public String update(@Login Object loginMember, @ModelAttribute("form") EslForm form, @PathVariable("eslId") Long id, Model model) {
        model.addAttribute("loginMember", loginMember);
        DoctorEslDto data = service.findDoctorById(id);
        form.updateInit(data);
        return "/admin/esl/edit";
    }

    @PostMapping("/{eslId}/edit")
    public String update(@Login Object loginMember, @ModelAttribute("form") EslForm form, Model model, @PathVariable("eslId") Long id) {
        model.addAttribute("loginMember", loginMember);
        DoctorInfoDto data = service.findDoctor(form.getName());
        if (!form.getPhoneNumber().equals(data.getPhoneNumber())) {
            form.input(data);
            return "/admin/esl/edit";
        }
        service.update(id, form.getName(), form.getRoom());
        return "redirect:/admin/esl";
    }

}
