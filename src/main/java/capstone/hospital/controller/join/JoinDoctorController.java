package capstone.hospital.controller.join;

import capstone.hospital.controller.join.form.CheckForm;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.dto.UploadFile;
import capstone.hospital.file.FileStore;
import capstone.hospital.controller.join.form.JoinDoctorForm;
import capstone.hospital.service.JoinService;
import capstone.hospital.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/join/doctor")
public class JoinDoctorController {

    private final JoinService joinService;
    private final FileStore fileStore;
    private final ValidateService validateService;

    @Value("file.dir")
    private String fileDir;

    // ModelAttribute
    @ModelAttribute("ranks")
    public DoctorRank[] itemTypes() {
        return DoctorRank.values();
    }

    @ModelAttribute("majors")
    public Major[] majorTypes() {
        return Major.values();
    }

    // mapping
    @GetMapping
    public String agreement(){
        return "join/doctor/termsAndConditions";
    }

    @PostMapping
    public String agreementCheck(){
        return "redirect:/join/doctor/phoneCheck";
    }

    @GetMapping("/phoneCheck")
    public String validate(@ModelAttribute("phone") CheckForm form) {
        return "join/doctor/phoneCheck";
    }

    @PostMapping("/phoneCheck")
    public String sendSms(@Validated @ModelAttribute("phone") CheckForm form, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        log.info("form={}", form);
        if (form.getPhoneNumber().isEmpty()) {
            bindingResult.reject("nullFail", "????????? ????????? ??????????????????.");
            return "join/doctor/phoneCheck";
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("checkForm") == null) {
            log.info("???????????? ??????");
//            String checkNumber = validateService.sendSMS(phoneNumber);
            String checkNumber = "123456";
            CheckForm checkForm = new CheckForm(form.getName(), form.getPhoneNumber(), form.getRrnFront(), form.getRrnBack(), checkNumber);
            session.setAttribute("checkForm", checkForm);
            session.setMaxInactiveInterval(3 * 60);
            return "join/doctor/phoneCheck";
        } else {
            CheckForm checkForm = (CheckForm) session.getAttribute("checkForm");
            if (checkForm.getCheckNumber().equals(form.getInputNumber())) {
                log.info("?????? ??????");
                // ?????? ??????
                try {
                    joinService.validateDuplicateRRN(checkForm.getRrn());
                } catch (IllegalStateException e) {
                    return "/join/joinFail";
                }

                redirectAttributes.addFlashAttribute("name", checkForm.getName());
                redirectAttributes.addFlashAttribute("phone", checkForm.getPhoneNumber());
                redirectAttributes.addFlashAttribute("rrnFront", checkForm.getRrnFront());

                log.info("session={}", session.getAttribute("checkForm"));
                session.invalidate();
                return "redirect:/join/doctor/joinForm";
            } else {
                log.info("?????? ??????");
                bindingResult.reject("checkFail", "?????? ????????? ?????? ????????????.");
                return "join/doctor/phoneCheck";
            }
        }
    }

    @GetMapping("/joinForm")
    public String joinDoctor(@ModelAttribute("doctor") JoinDoctorForm form, Model model) {
        form.setName((String) model.asMap().get("name"));
        form.setPhoneNumber((String) model.asMap().get("phone"));
        form.setRrnFront((String) model.asMap().get("rrnFront"));
        return "join/doctor/userJoinDoctor";
    }

    @PostMapping("/joinForm")
    public String saveDoctor(@Validated @ModelAttribute("doctor") JoinDoctorForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        log.info("file={}", form.getFile());

        // ????????? ?????? ??????
        try {
            joinService.validateDuplicateLoginId(form.getLoginId());
        } catch (IllegalStateException e) {
            log.info("idCheckFail");
            bindingResult.reject("idCheckFail", e.getMessage());
            return "join/patient/userJoinNurse";
        }

        if (bindingResult.hasErrors()) {
            return "join/doctor/userJoinDoctor";
        }

        // ???????????? ??????
        if (!form.getLoginPw().equals(form.getCheckPw())) {
            bindingResult.reject("pwCheckFail", "????????? ??????????????? ????????????.");
            return "join/doctor/userJoinDoctor";
        }

        // ?????? ?????? ??????
        try {
            joinService.validateDuplicateRRN(form.getRrnFront() + "-" + form.getRrnBack());
        } catch (IllegalStateException e) {
            bindingResult.reject("rrnCheckFail", e.getMessage());
            return "join/patient/userJoinPatient";
        }

        // success
        doctorSave(form);

        redirectAttributes.addFlashAttribute("name", form.getName());
        return "redirect:/join/success";
    }

    private void doctorSave(JoinDoctorForm form) throws IOException {
        UploadFile attachFile = fileStore.storeFile(form.getFile());
        Address newAddress = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Information newInfo = new Information(form.getName(), form.getRrnFront(), form.getRrnBack(), form.getPhoneNumber(), newAddress);
        Doctor doctor = new Doctor(form.getLoginId(), form.getLoginPw(), form.getUniversity(), newInfo, form.getLicenseCode(), form.getMajor(), form.getRank(), attachFile);
        joinService.joinDoctor(doctor);
    }
}
