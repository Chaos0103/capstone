package capstone.hospital.controller.join;

import capstone.hospital.controller.join.form.PatientCheckForm;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.form.JoinPatientForm;
import capstone.hospital.service.JoinService;
import capstone.hospital.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/join/patient")
public class JoinPatientController {

    private final JoinService joinService;
    private final ValidateService validateService;

    /**
     * 환자 회원 가입
     */
    @GetMapping
    public String agreement(){
        return "join/patient/termsAndConditions";
    }

    @PostMapping
    public String agreementCheck(){
        return "redirect:/join/patient/phoneCheck";
    }

    @GetMapping("/phoneCheck")
    public String validate(@ModelAttribute("phone") PatientCheckForm form) {
        return "join/patient/phoneCheck";
    }

    @PostMapping("/phoneCheck")
    public String sendSms(@ModelAttribute("phone") PatientCheckForm form, BindingResult bindingResult, HttpServletRequest request) {

        log.info("form={}", form);
        if (form.getPhoneNumber().isEmpty()) {
            bindingResult.reject("nullFail", "핸드폰 번호를 입력해주세요.");
            return "join/patient/phoneCheck";
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("checkForm") == null) {
            log.info("인증번호 발송");
//            String checkNumber = validateService.sendSMS(phoneNumber);
            String checkNumber = "1234";
            PatientCheckForm checkForm = new PatientCheckForm(form.getName(), form.getPhoneNumber(), checkNumber);
            session.setAttribute("checkForm", checkForm);
            session.setMaxInactiveInterval(3 * 60);
            return "join/patient/phoneCheck";
        } else {
            PatientCheckForm checkForm = (PatientCheckForm) session.getAttribute("checkForm");
            if (checkForm.getCheckNumber().equals(form.getInputNumber())) {
                log.info("인증 성공");
                form.setOnOff(true);
            } else {
                log.info("인증 실패");
                bindingResult.reject("checkFail", "인증 번호가 맞지 않습니다.");
            }
            return "join/patient/phoneCheck";
        }
    }

    @GetMapping("/joinForm")
    public String joinPatient(@ModelAttribute("patient") JoinPatientForm form) {
        return "join/patient/userJoinPatient";
    }

    @PostMapping("/joinForm")
    public String savePatient(@Validated @ModelAttribute("patient") JoinPatientForm form, BindingResult bindingResult, HttpServletRequest request) {

        log.info("input={}", form);

        if (bindingResult.hasErrors()) {
            return "join/patient/userJoinPatient";
        }

        // 아이디 중복 체크
        boolean idCheck = joinService.validateDuplicateLoginId(form.getLoginId());
        if (!idCheck) {
            bindingResult.reject("idCheckFail", "이미 사용중인 아이디입니다.");
            return "join/patient/userJoinPatient";
        }

        // 비밀번호 확인
        if (!form.getLoginPw().equals(form.getCheckPw())) {
            bindingResult.reject("pwCheckFail", "입력된 비밀번호가 다릅니다.");
            return "join/patient/userJoinPatient";
        }

        // 회원 중복 체크
        boolean rrnCheck = joinService.validateDuplicateRRN(form.getRrnFront() + "-" + form.getRrnBack());
        if (!rrnCheck) {
            bindingResult.reject("rrnCheckFail", "이미 가입된 회원입니다.");
            return "join/patient/userJoinPatient";
        }

        // success
        String phoneNumber = form.getPhoneNumberFront() + form.getPhoneNumberMid() + form.getPhoneNumberBack();
        Address newAddress = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Information newInfo = new Information(form.getName(), form.getRrnFront(), form.getRrnBack(), phoneNumber, newAddress);
        Patient patient = new Patient(form.getLoginId(), form.getLoginPw(), newInfo);
        Long savedId = joinService.joinPatient(patient);

        HttpSession session = request.getSession();
        session.setAttribute("name", form.getName());
        return "redirect:/join/success";
    }


}
