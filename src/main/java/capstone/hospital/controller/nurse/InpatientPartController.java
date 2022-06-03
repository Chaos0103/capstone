package capstone.hospital.controller.nurse;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.doctor.form.SearchForm;
import capstone.hospital.controller.nurse.form.InpatientForm;
import capstone.hospital.domain.enumtype.BloodType;
import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.WardType;
import capstone.hospital.dto.CreateInpatientDto;
import capstone.hospital.dto.CreateInpatientInfoDto;
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
@RequestMapping("/nurse/inpatient")
public class InpatientPartController {

    private final NurseService nurseService;

    // ModelAttribute
    @ModelAttribute("bloods")
    public BloodType[] bloodTypes() {
        return BloodType.values();
    }

    @ModelAttribute("wards")
    public WardType[] wardTypes() {
        return WardType.values();
    }

    // Mapping
    @GetMapping
    public String home(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        List<CreateInpatientDto> inpatients = nurseService.findWaitInpatient(form.getName());
        model.addAttribute("inpatients", inpatients);
        return "/nurse/inpatient/create";
    }

    @GetMapping("/create/{inpatientId}")
    private String info(@Login Object loginMember, @PathVariable Long inpatientId,
                        @ModelAttribute("form") InpatientForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("info", nurseService.findOneInpatient(inpatientId));
        return "/nurse/inpatient/info";
    }

    @PostMapping("/create/{inpatientId}")
    private String create(@PathVariable Long inpatientId,
                          @ModelAttribute("form") InpatientForm form) {
        nurseService.createInpatient(inpatientId, new CreateInpatientInfoDto(form));
        return "redirect:/nurse/inpatient";
    }
}
