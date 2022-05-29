package capstone.hospital.controller.nurse;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.nurse.form.SearchForm;
import capstone.hospital.dto.InpatientInfoDto;
import capstone.hospital.dto.PatientInfoDto;
import capstone.hospital.dto.ReportDto;
import capstone.hospital.exception.SearchException;
import capstone.hospital.service.NurseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
        try {
            info = service.findInpatientInfo(form.getName());
        } catch (SearchException e) {
            log.info("error message={}", e.getMessage());
        } finally {
            List<ReportDto> reports = service.findReport(info.getId());
            model.addAttribute("info", info);
            model.addAttribute("reports", reports);
        }
        return "/nurse/chart/main";
    }
}
