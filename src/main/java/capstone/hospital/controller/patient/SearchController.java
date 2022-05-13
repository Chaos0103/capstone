package capstone.hospital.controller.patient;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.patient.form.SearchForm;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class SearchController {

    private final SearchService searchService;

    @ModelAttribute("majors")
    public Major[] majorTypes() {
        return Major.values();
    }

    @GetMapping("/doctorSearch")
    public String doctorSearch(@Login Object loginMember, @ModelAttribute("form") SearchForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("doctors", searchService.doctorSearch(form));
        return "patient/doctorSearch";
    }
}
