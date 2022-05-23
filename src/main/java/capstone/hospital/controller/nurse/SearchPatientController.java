package capstone.hospital.controller.nurse;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.nurse.form.SearchForm;
import capstone.hospital.dto.PatientInfoDto;
import capstone.hospital.dto.ReportDto;
import capstone.hospital.service.NurseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/nurse/patient")
public class SearchPatientController {

    private final NurseService service;

    @GetMapping
    public String information(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        PatientInfoDto info = service.findPatientInfo(form.getName());
        List<ReportDto> reports = service.findReport(info.getId());
        model.addAttribute("info", info);
        model.addAttribute("reports", reports);
        return "/nurse/patient/info";
    }
}
