package capstone.hospital.controller.doctor;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.doctor.form.PrescriptionForm;
import capstone.hospital.controller.doctor.form.ReportForm;
import capstone.hospital.controller.doctor.form.SearchForm;
import capstone.hospital.controller.doctor.form.WardForm;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.enumtype.WardType;
import capstone.hospital.dto.DiseaseDto;
import capstone.hospital.dto.PatientInfoDto;
import capstone.hospital.dto.PrescriptionDto;
import capstone.hospital.dto.ReportDto;
import capstone.hospital.exception.SearchException;
import capstone.hospital.service.DiseaseService;
import capstone.hospital.service.DoctorService;
import capstone.hospital.service.InpatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor/patient")
public class PatientController {

    private final DoctorService doctorService;
    private final DiseaseService diseaseService;
    private final InpatientService inpatientService;
    private static List<PrescriptionDto> prescriptions = new ArrayList<>();

    @ModelAttribute("wards")
    public WardType[] wardTypes() {
        return WardType.values();
    }

    @GetMapping
    public String information(@Login Object loginMember, @ModelAttribute("form") SearchForm form, @ModelAttribute("ward") WardForm wardForm, Model model) {
        model.addAttribute("loginMember", loginMember);
        Long patientId = (Long) model.asMap().get("patientId");
        PatientInfoDto info = null;
        if (patientId == null) {
            info = doctorService.findPatientInfo(form.getName());
        } else {
            info = doctorService.findPatientInfoById(patientId);
            form.setName(info.getName());
        }
        List<ReportDto> reports = doctorService.findReport(info.getId());
        model.addAttribute("info", info);
        model.addAttribute("reports", reports);
        return "/doctor/patient/info";
    }

    @PostMapping
    public String createInpatient(@Login Object loginMember, @RequestParam("name") String name, @ModelAttribute("ward") WardForm wardForm, RedirectAttributes redirectAttributes) {
        PatientInfoDto patientInfo = doctorService.findPatientInfo(name);
        log.info("patient={}", patientInfo);
        log.info("ward={}", wardForm);

        inpatientService.createInpatient(patientInfo.getId(), ((Doctor)loginMember).getId(), wardForm.getReportId(), wardForm.getType());
        return "redirect:/doctor/patient";
    }

    @GetMapping("/report/{patientId}/create")
    public String createReport(@Login Object loginMember,
                               @ModelAttribute("form") ReportForm form,
                               @ModelAttribute("prescription") PrescriptionForm prescription,
                               @PathVariable("patientId") Long patientId, Model model) {
        model.addAttribute("loginMember", loginMember);
        ReportForm report = (ReportForm) model.asMap().get("report");
        if (report != null) {
            form.input(report);
            model.addAttribute("prescriptions", prescriptions);
        }
        return "/doctor/patient/createReport";
    }

    @PostMapping("/report/{patientId}/create")
    public String save(@Login Object loginMember,
                       @ModelAttribute("form") ReportForm report,
                       @ModelAttribute("prescription") PrescriptionForm prescription,
                       @PathVariable("patientId") Long patientId,
                       RedirectAttributes redirectAttributes, Model model) {
        if (report.getCode().isEmpty()) {
            try {
                DiseaseDto data = diseaseService.findCode(report.getName());
                report.input(data);
                redirectAttributes.addFlashAttribute("report", report);
            } catch (SearchException e) {
                log.info("error message={}", e.getMessage());
            }
            return "redirect:/doctor/patient/report/{patientId}/create";
        }
        if (!prescription.getMedicineCode().isEmpty()) {
            prescriptions.add(new PrescriptionDto(prescription));
            redirectAttributes.addFlashAttribute("report", report);
            return "redirect:/doctor/patient/report/{patientId}/create";
        }

        doctorService.report(patientId, ((Doctor) loginMember).getId(), report.getCode(), report.getContent(), prescriptions);
        prescriptions = new ArrayList<>();
        redirectAttributes.addFlashAttribute("patientId", patientId);
        return "redirect:/doctor/patient";
    }

}
