package capstone.hospital.service;

import capstone.hospital.domain.Patient;
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
    private final PatientQueryRepository patientQueryRepository;
    private final DoctorQueryRepository doctorQueryRepository;
    private final NurseQueryRepository nurseQueryRepository;

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
        List<String> loginId = patientQueryRepository.findLoginId(name, phoneNumber);
        if (loginId.isEmpty()) {
            loginId = doctorQueryRepository.findLoginId(name, phoneNumber);
        }
        if (loginId.isEmpty()) {
            loginId = nurseQueryRepository.findLoginId(name, phoneNumber);
        }
        if (loginId.isEmpty()) {
//            throw new IllegalStateException("존재하지 않는 회원입니다.");
            return "존재하지 않는 회원입니다.";
        }
        return name + "님의 아이디는 " + loginId.get(0) + "입니다.";
    }

    public String findLoginPw(String name, String phoneNumber, String loginId) {
        List<String> loginPw = patientQueryRepository.findLoginPw(name, phoneNumber, loginId);
        if (loginPw.isEmpty()) {
            loginPw = doctorQueryRepository.findLoginPw(name, phoneNumber, loginId);
        }
        if (loginPw.isEmpty()) {
            loginPw = nurseQueryRepository.findLoginPw(name, phoneNumber, loginId);
        }
        if (loginPw.isEmpty()) {
//            throw new IllegalStateException("존재하지 않는 회원입니다.");
            return "존재하지 않는 회원입니다.";
        }
        return name + "님의 비밀번호는 " + loginPw.get(0) + "입니다.";
    }


}
