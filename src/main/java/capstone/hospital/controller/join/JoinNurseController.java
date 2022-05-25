package capstone.hospital.controller.join;

import capstone.hospital.controller.join.form.CheckForm;
import capstone.hospital.domain.Nurse;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.controller.join.form.JoinNurseForm;
import capstone.hospital.dto.UploadFile;
import capstone.hospital.file.FileStore;
import capstone.hospital.service.JoinService;
import capstone.hospital.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/join/nurse")
public class JoinNurseController {

    private final JoinService joinService;
    private final FileStore fileStore;
    private final ValidateService validateService;

    @ModelAttribute("majors")
    public Major[] majorTypes() {
        return Major.values();
    }

    // mapping
    @GetMapping
    public String agreement(){
        return "join/nurse/termsAndConditions";
    }

    @PostMapping
    public String agreementCheck(){
        return "redirect:/join/nurse/phoneCheck";
    }

    @GetMapping("/phoneCheck")
    public String validate(@ModelAttribute("phone") CheckForm form) {
        return "join/nurse/phoneCheck";
    }

    @PostMapping("/phoneCheck")
    public String sendSms(@Validated @ModelAttribute("phone") CheckForm form, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        log.info("form={}", form);
        if (form.getPhoneNumber().isEmpty()) {
            bindingResult.reject("nullFail", "핸드폰 번호를 입력해주세요.");
            return "join/nurse/phoneCheck";
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("checkForm") == null) {
            log.info("인증번호 발송");
//            String checkNumber = validateService.sendSMS(phoneNumber);
            String checkNumber = "123456";
            CheckForm checkForm = new CheckForm(form.getName(), form.getPhoneNumber(), form.getRrnFront(), form.getRrnBack(), checkNumber);
            session.setAttribute("checkForm", checkForm);
            session.setMaxInactiveInterval(3 * 60);
            return "join/nurse/phoneCheck";
        } else {
            CheckForm checkForm = (CheckForm) session.getAttribute("checkForm");
            if (checkForm.getCheckNumber().equals(form.getInputNumber())) {
                log.info("인증 성공");
                // 중복 검사
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
                return "redirect:/join/nurse/joinForm";
            } else {
                log.info("인증 실패");
                bindingResult.reject("checkFail", "인증 번호가 맞지 않습니다.");
                return "join/nurse/phoneCheck";
            }
        }
    }

    @GetMapping("/joinForm")
    public String joinDoctor(@ModelAttribute("nurse") JoinNurseForm form, Model model) {
        form.setName((String) model.asMap().get("name"));
        form.setPhoneNumber((String) model.asMap().get("phone"));
        form.setRrnFront((String) model.asMap().get("rrnFront"));
        return "join/nurse/userJoinNurse";
    }

    @PostMapping("/joinForm")
    public String saveNurse(@Validated @ModelAttribute("nurse") JoinNurseForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        // 아이디 중복 체크
        try {
            joinService.validateDuplicateLoginId(form.getLoginId());
        } catch (IllegalStateException e) {
            log.info("idCheckFail");
            bindingResult.reject("idCheckFail", e.getMessage());
            return "join/patient/userJoinNurse";
        }

        if (bindingResult.hasErrors()) {
            return "join/nurse/userJoinNurse";
        }

        // 비밀번호 확인
        if (!form.getLoginPw().equals(form.getCheckPw())) {
            bindingResult.reject("pwCheckFail", "입력된 비밀번호가 다릅니다.");
            return "join/nurse/userJoinNurse";
        }

        // 회원 중복 체크
        try {
            joinService.validateDuplicateRRN(form.getRrnFront() + "-" + form.getRrnBack());
        } catch (IllegalStateException e) {
            bindingResult.reject("rrnCheckFail", e.getMessage());
            return "join/patient/userJoinPatient";
        }

        // success
        nurseSave(form);

        redirectAttributes.addFlashAttribute("name", form.getName());
        return "redirect:/join/success";
    }

    private void nurseSave(JoinNurseForm form) throws IOException {
        UploadFile attachFile = fileStore.storeFile(form.getFile());
        Address newAddress = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Information newInfo = new Information(form.getName(), form.getRrnFront(), form.getRrnBack(), form.getPhoneNumber(), newAddress);
        Nurse nurse = new Nurse(form.getLoginId(), form.getLoginPw(), newInfo, form.getLicenseCode(), form.getMajor(), attachFile);
        joinService.joinNurse(nurse);
    }
}
