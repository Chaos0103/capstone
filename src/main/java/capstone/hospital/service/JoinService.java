package capstone.hospital.service;

import capstone.hospital.domain.Admin;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.Nurse;
import capstone.hospital.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JoinService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public Long joinPatient(Patient patient) {
        validateDuplicateRRN(patient.getInfo().getRrn());
        validateDuplicateLoginId(patient.getLoginId());
        patientRepository.save(patient);
        return patient.getId();
    }

    @Transactional
    public Long joinDoctor(Doctor doctor) {
        validateDuplicateRRN(doctor.getInfo().getRrn());
        validateDuplicateLoginId(doctor.getLoginId());
        doctorRepository.save(doctor);
        return doctor.getId();
    }

    @Transactional
    public Long joinNurse(Nurse nurse) {
        validateDuplicateRRN(nurse.getInfo().getRrn());
        validateDuplicateLoginId(nurse.getLoginId());
        nurseRepository.save(nurse);
        return nurse.getId();
    }

    @Transactional  // 임시
    public Long joinAdmin(Admin admin) {
//        validateDuplicateRRN(nurse.getInfo().getRrn(), 2);
//        validateDuplicateLoginId(nurse.getLoginId());
        adminRepository.save(admin);
        return admin.getId();
    }

    //== 검증 메서드 ==//
    public void validateDuplicateRRN(String Rrn) {

        Object findMember = patientRepository.findByInfoRrn(Rrn)
                .orElse(null);
        if (findMember == null) {
            findMember = doctorRepository.findByInfoRrn(Rrn)
                    .orElse(null);
        }
        if (findMember == null) {
            findMember = nurseRepository.findByInfoRrn(Rrn)
                    .orElse(null);
        }

        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public void validateDuplicateLoginId(String loginId) {

        Object findMember = patientRepository.findByLoginId(loginId)
                .orElse(null);
        if (findMember == null) {
            findMember = doctorRepository.findByLoginId(loginId)
                    .orElse(null);
        }
        if (findMember == null) {
            findMember = nurseRepository.findByLoginId(loginId)
                    .orElse(null);
        }
        if (findMember == null) {
            findMember = adminRepository.findByLoginId(loginId)
                    .orElse(null);
        }

        if (findMember != null) {
            throw new IllegalStateException("이미 사용중인 아이디입니다.");
        }
    }
}
