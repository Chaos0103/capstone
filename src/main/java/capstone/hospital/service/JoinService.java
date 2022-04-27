package capstone.hospital.service;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.Nurse;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.PatientQueryRepository;
import capstone.hospital.repository.PatientRepository;
import capstone.hospital.repository.NurseRepository;
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
    private final PatientQueryRepository patientQueryRepository;

    @Transactional  // type: 0
    public Long joinPatient(Patient patient) {
        validateDuplicateRRN(patient.getInfo().getRrn(), 0);
        validateDuplicateLoginId(patient.getLoginId());
        patientRepository.save(patient);
        return patient.getId();
    }

    @Transactional  // type: 1
    public Long joinDoctor(Doctor doctor) {
        validateDuplicateRRN(doctor.getInfo().getRrn(), 1);
        validateDuplicateLoginId(doctor.getLoginId());
        doctorRepository.save(doctor);
        return doctor.getId();
    }

    @Transactional  // type: 2
    public Long joinNurse(Nurse nurse) {
        validateDuplicateRRN(nurse.getInfo().getRrn(), 2);
        validateDuplicateLoginId(nurse.getLoginId());
        nurseRepository.save(nurse);
        return nurse.getId();
    }


    //== 검증 메서드 ==//
    private void validateDuplicateRRN(String RRN, int type) {

        List<String> find = new ArrayList<>();
        if (type == 0) {
            find = patientQueryRepository.findByRrn(RRN);
        } else if (type == 1) {
            find = patientQueryRepository.findByRrn(RRN);
        } else if (type == 2) {
            find = patientQueryRepository.findByRrn(RRN);
        }
        if (!find.isEmpty()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    private void validateDuplicateLoginId(String loginId) {
        Optional<Patient> findMember = patientRepository.findByLoginId(loginId);
        if (findMember.isPresent()) {
            throw new IllegalStateException("이미 사용중인 아이디입니다.");
        }
    }
}
