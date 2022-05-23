package capstone.hospital.service;

import capstone.hospital.exception.LoginException;
import capstone.hospital.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final AdminRepository adminRepository;

    public Object login(String loginId, String loginPw) {

        Object findMember = patientRepository.findByLoginId(loginId)
                .filter(patient -> patient.getLoginPw().equals(loginPw))
                .orElse(null);
        if (findMember == null) {
            findMember = doctorRepository.findByLoginId(loginId)
                    .filter(patient -> patient.getLoginPw().equals(loginPw))
                    .orElse(null);
        }
        if (findMember == null) {
            findMember = nurseRepository.findByLoginId(loginId)
                    .filter(patient -> patient.getLoginPw().equals(loginPw))
                    .orElse(null);
        }
        if (findMember == null) {
            findMember = adminRepository.findByLoginId(loginId)
                    .filter(patient -> patient.getLoginPw().equals(loginPw))
                    .orElse(null);
        }

        return findMember;
    }

    public String findLoginId(String name, String phoneNumber) {
        List<String> loginId = patientRepository.findLoginId(name, phoneNumber);
        // 수정중
//        if (loginId.isEmpty()) {
//            List<Object> test = doctorRepository.findByInfoNameAndInfoPhoneNumber(name, phoneNumber);
//            Doctor doctor = (Doctor) test.get(0);
//            loginId.add(doctor.getLoginId());
//        }
        if (loginId.isEmpty()) {
            loginId = nurseRepository.findLoginId(name, phoneNumber);
        }
        if (loginId.isEmpty()) {
            throw new LoginException("존재하지 않는 회원입니다.");
        }
        return name + "님의 아이디는 " + loginId.get(0) + "입니다.";
    }

    public String findLoginPw(String name, String phoneNumber, String loginId) {
        List<String> loginPw = patientRepository.findLoginPw(name, phoneNumber, loginId);
        if (loginPw.isEmpty()) {
            loginPw = doctorRepository.findLoginPw(name, phoneNumber, loginId);
        }
        if (loginPw.isEmpty()) {
            loginPw = nurseRepository.findLoginPw(name, phoneNumber, loginId);
        }
        if (loginPw.isEmpty()) {
            throw new LoginException("존재하지 않는 회원입니다.");
        }
        return name + "님의 비밀번호는 " + loginPw.get(0) + "입니다.";
    }


}
