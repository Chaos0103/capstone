package capstone.hospital.controller.admin;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.admin.form.ATCCodeForm;
import capstone.hospital.controller.admin.form.SearchForm;
import capstone.hospital.domain.ATCCode;
import capstone.hospital.service.MedicineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/medicine")
public class MedicinePartController {

    private final MedicineService medicineService;

    @GetMapping
    public String list(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("list", medicineService.list(form.getName()));
        return "/admin/medicine/list";
    }

    @GetMapping("/{code}/delete")
    public String delete(@PathVariable("code") String code) {
        medicineService.delete(code);
        return "redirect:/admin/medicine";
    }

    @GetMapping("/create")
    public String create(@Login Object loginMember, @ModelAttribute("form") ATCCodeForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/admin/medicine/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("form") ATCCodeForm form) {
        medicineService.save(form.getCode(), form.getName(), form.getCompany(), form.getType(), form.getStock());
        return "redirect:/admin/medicine";
    }

    @GetMapping("/{code}/edit")
    public String edit(@Login Object loginMember, @ModelAttribute("form") ATCCodeForm form, @PathVariable("code") String code, Model model) {
        model.addAttribute("loginMember", loginMember);
        ATCCode editCode = medicineService.findEditCode(code);
        form.setCode(editCode.getCode());
        form.setName(editCode.getName());
        form.setCompany(editCode.getCompany());
        form.setType(editCode.getType());
        form.setStock(editCode.getStock());
        return "/admin/medicine/edit";
    }

    @PostMapping("/{code}/edit")
    public String update(@ModelAttribute("form") ATCCodeForm form) {
        medicineService.save(form.getCode(), form.getName(), form.getCompany(), form.getType(), form.getStock());
        return "redirect:/admin/medicine";
    }
}
