package capstone.hospital.controller.admin;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.admin.form.KCDCodeForm;
import capstone.hospital.controller.admin.form.SearchForm;
import capstone.hospital.domain.KCDCode;
import capstone.hospital.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/disease")
public class DiseasePartController {

    private final DiseaseService diseaseService;

    @GetMapping
    public String list(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("list", diseaseService.list(form.getName()));
        return "/admin/disease/list";
    }

    @GetMapping("/{code}/delete")
    public String delete(@PathVariable("code") String code) {
        diseaseService.delete(code);
        return "redirect:/admin/disease";
    }

    @GetMapping("/create")
    public String create(@Login Object loginMember, @ModelAttribute("form") KCDCodeForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/admin/disease/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("form") KCDCodeForm form) {
        diseaseService.save(form.getCode(), form.name);
        return "redirect:/admin/medicine";
    }

    @GetMapping("/{code}/edit")
    public String edit(@Login Object loginMember, @ModelAttribute("form") KCDCodeForm form, @PathVariable("code") String code, Model model) {
        model.addAttribute("loginMember", loginMember);
        KCDCode editCode = diseaseService.findEditCode(code);
        form.setCode(editCode.getCode());
        form.setName(editCode.getName());
        return "/admin/disease/edit";
    }

    @PostMapping("/{code}/edit")
    public String update(@ModelAttribute("form") KCDCodeForm form) {
        diseaseService.save(form.getCode(), form.getName());
        return "redirect:/admin/disease";
    }


}
