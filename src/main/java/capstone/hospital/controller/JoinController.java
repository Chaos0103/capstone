package capstone.hospital.controller;

import capstone.hospital.form.JoinDoctorForm;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.form.JoinPatientForm;
import capstone.hospital.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinController {

    private final JoinService joinService;

    @ModelAttribute("ranks")
    public DoctorRank[] itemTypes() {
        return DoctorRank.values();
    }

    @ModelAttribute("majors")
    public Major[] majorTypes() {
        return Major.values();
    }

    @GetMapping
    public String joinSelect() {
        log.info("joinSelect success");
        return "join/userJobSelect";
    }

    @GetMapping("/patient")
    public String joinPatient(@ModelAttribute("patient") JoinPatientForm form) {
        log.info("joinPatient success");
        return "join/userJoinPatient";
    }

    @PostMapping("/patient")
    public String savePatient(@Validated @ModelAttribute("patient") JoinPatientForm form, BindingResult bindingResult) {
        log.info("savePatient");

        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "join/userJoinPatient";
        }

        // success
        Address newAddress = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Information newInfo = new Information(form.getName(), form.getRrnFront(), form.getRrnBack(), form.getPhoneNumber(), newAddress);
        Patient patient = new Patient(form.getLoginId(), form.getLoginPw(), newInfo);
        Long savedId = joinService.joinPatient(patient);
        return "redirect:/";
    }


    @GetMapping("/doctor")
    public String joinDoctor(@ModelAttribute("doctor") JoinDoctorForm form) {
        log.info("joinDoctor success");
        return "join/userJoinDoctor";
    }

//    @PostMapping("/patient")
//    public String saveDoctor(@Validated @ModelAttribute("doctor") JoinDoctorForm form) {
//        log.info("saveDoctor");
//
//         success
//        Address newAddress = new Address(form.getCity(), form.getStreet(), form.getZipcode());
//        Information newInfo = new Information(form.getName(), form.getRrnFront(), form.getRrnBack(), form.getPhoneNumber(), newAddress);
//        new Doctor(form.getLoginId(), form.getLoginPw(), newInfo);
//        Long savedId = joinService.joinDoctor(patient);
//        return "redirect:/";
//    }

    @GetMapping("/nurse")
    public String joinNurse() {
        log.info("joinNurse success");
        return "join/userJoinNurse";
    }
}
