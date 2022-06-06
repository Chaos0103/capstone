package capstone.hospital.controller.nurse;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.nurse.form.SearchForm;
import capstone.hospital.domain.Chart;
import capstone.hospital.domain.Nurse;
import capstone.hospital.dto.InpatientInfoDto;
import capstone.hospital.dto.PatientInfoDto;
import capstone.hospital.dto.PrescriptionDto;
import capstone.hospital.dto.ReportDto;
import capstone.hospital.exception.SearchException;
import capstone.hospital.service.NurseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/nurse/chart")
public class ChartPartController {

    private final NurseService service;

    @GetMapping
    public String main(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        InpatientInfoDto info = new InpatientInfoDto();
        List<PrescriptionDto> prescription = new ArrayList<>();
        List<Chart> charts = new ArrayList<>();
        try {
            info = service.findInpatientInfo(form.getName());
            prescription = service.findPrescription(info.getId());
            charts = service.findAllChart(info.getId());
        } catch (SearchException e) {
            log.info("error message={}", e.getMessage());
        } finally {
            model.addAttribute("info", info);
            model.addAttribute("charts", charts);
            model.addAttribute("prescription", prescription);
        }
        return "/nurse/chart/main";
    }

    @GetMapping("/{id}/inject")
    public String inject(@Login Object loginMember, Model model, @PathVariable Long id) {
        model.addAttribute("loginMember", loginMember);
        service.createChart(id, ((Nurse) loginMember).getId());
        return "redirect:/nurse/chart";
    }

    @GetMapping("/{id}/out")
    public String out(@Login Object loginMember, Model model, @PathVariable Long id) {
        model.addAttribute("loginMember", loginMember);
        service.outInpatient(id);
        return "redirect:/nurse/chart";
    }
}
