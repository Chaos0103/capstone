package capstone.hospital.controller.nurse;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.doctor.form.SearchForm;
import capstone.hospital.domain.Inpatient;
import capstone.hospital.dto.InpatientInfoDto;
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
@RequestMapping("/nurse/inpatient")
public class InpatientPartController {

    private final NurseService nurseService;

    @GetMapping
    public String home(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        List<InpatientInfoDto> inpatients = nurseService.findWaitInpatient(form.getName());
        model.addAttribute("inpatients", inpatients);
        return "/nurse/inpatient/create";
    }
}
