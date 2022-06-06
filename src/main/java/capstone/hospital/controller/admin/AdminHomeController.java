package capstone.hospital.controller.admin;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.domain.enumtype.ATCType;
import capstone.hospital.service.AdminService;
import capstone.hospital.service.DiseaseService;
import capstone.hospital.service.MedicineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminHomeController {

    private final AdminService adminService;
    private final MedicineService medicineService;
    private final DiseaseService diseaseService;

    @GetMapping
    public String adminHome(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        int nurseCnt = adminService.readNurse(null).size();
        int doctorCnt = adminService.readDoctor(null).size();
        int total = nurseCnt + doctorCnt;
        model.addAttribute("total", total);
        model.addAttribute("doctorCnt", doctorCnt);
        model.addAttribute("nurseCnt", nurseCnt);
        model.addAttribute("waitDoctor", adminService.waitDoctor(null, 0, 100).size());
        model.addAttribute("waitNurse", adminService.waitNurse(null).size());

        int capsule = medicineService.count(ATCType.CAPSULE);
        int liquid = medicineService.count(ATCType.LIQUID);
        int pill = medicineService.count(ATCType.PILL);
        int totalMedical = capsule + liquid + pill;
        model.addAttribute("totalMedical", totalMedical);
        model.addAttribute("capsule", capsule);
        model.addAttribute("liquid", liquid);
        model.addAttribute("pill", pill);

        int diseaseCnt = diseaseService.count();
        model.addAttribute("diseaseCnt", diseaseCnt);

        return "/admin/home";
    }
}
