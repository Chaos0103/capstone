package capstone.hospital.controller;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Nurse;
import capstone.hospital.form.JoinDoctorForm;
import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.form.JoinNurseForm;
import capstone.hospital.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinController {

    private final JoinService joinService;

    // ModelAttribute
    @ModelAttribute("ranks")
    public DoctorRank[] itemTypes() {
        return DoctorRank.values();
    }

    @ModelAttribute("majors")
    public Major[] majorTypes() {
        return Major.values();
    }

    // Mapping
    @GetMapping
    public String joinSelect() {
        return "join/userJobSelect";
    }


    @GetMapping("/doctor")
    public String joinDoctor(@ModelAttribute("doctor") JoinDoctorForm form) {
        return "join/doctor/userJoinDoctor";
    }

    @PostMapping("/doctor")
    public String saveDoctor(@Validated @ModelAttribute("doctor") JoinDoctorForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "join/userJoinDoctor";
        }

        // 아이디 중복 체크
        boolean idCheck = joinService.validateDuplicateLoginId(form.getLoginId());
        if (!idCheck) {
            bindingResult.reject("idCheckFail", "이미 사용중인 아이디입니다.");
            return "join/userJoinDoctor";
        }

        // 비밀번호 확인
        if (form.getLoginPw().equals(form.getCheckPw())) {
            bindingResult.reject("pwCheckFail", "입력된 비밀번호가 다릅니다.");
            return "join/userJoinDoctor";
        }

        // 회원 중복 체크
        boolean rrnCheck = joinService.validateDuplicateRRN(form.getRrnFront() + "-" + form.getRrnBack());
        if (!rrnCheck) {
            bindingResult.reject("rrnCheckFail", "이미 가입된 회원입니다.");
            return "join/userJoinDoctor";
        }

        // success
        Address newAddress = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Information newInfo = new Information(form.getName(), form.getRrnFront(), form.getRrnBack(), form.getPhoneNumber(), newAddress);
        Doctor doctor = new Doctor(form.getLoginId(), form.getLoginPw(), newInfo, form.getLicenseCode(), form.getMajor(), form.getRank());
        Long savedId = joinService.joinDoctor(doctor);
        return "redirect:/";
    }

    @GetMapping("/nurse")
    public String joinNurse(@ModelAttribute("nurse") JoinNurseForm form) {
        return "join/userJoinNurse";
    }

    @PostMapping("/nurse")
    public String saveNurse(@Validated @ModelAttribute("nurse") JoinNurseForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "join/userJoinNurse";
        }

        // 아이디 중복 체크
        boolean idCheck = joinService.validateDuplicateLoginId(form.getLoginId());
        if (!idCheck) {
            bindingResult.reject("idCheckFail", "이미 사용중인 아이디입니다.");
            return "join/userJoinNurse";
        }

        // 비밀번호 확인
        if (form.getLoginPw().equals(form.getCheckPw())) {
            bindingResult.reject("pwCheckFail", "입력된 비밀번호가 다릅니다.");
            return "join/userJoinNurse";
        }

        // 회원 중복 체크
        boolean rrnCheck = joinService.validateDuplicateRRN(form.getRrnFront() + "-" + form.getRrnBack());
        if (!rrnCheck) {
            bindingResult.reject("rrnCheckFail", "이미 가입된 회원입니다.");
            return "join/userJoinNurse";
        }

        // success
        Address newAddress = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Information newInfo = new Information(form.getName(), form.getRrnFront(), form.getRrnBack(), form.getPhoneNumber(), newAddress);
        Nurse nurse = new Nurse(form.getLoginId(), form.getLoginPw(), newInfo, form.getLicenseCode(), form.getMajor());
        Long savedId = joinService.joinNurse(nurse);
        return "redirect:/";
    }

    @GetMapping("/success")
    public String success(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String name = (String) session.getAttribute("name");
        model.addAttribute("title", "비밀번호 찾기");
        model.addAttribute("name", name);
        return "join/joinSuccess";
    }
}
