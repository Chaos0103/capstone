package capstone.hospital.controller.admin;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.admin.form.EslForm;
import capstone.hospital.controller.admin.form.SearchForm;
import capstone.hospital.service.DoctorEslService;
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

}
