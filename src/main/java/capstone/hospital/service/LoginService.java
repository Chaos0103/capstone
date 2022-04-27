package capstone.hospital.service;

import capstone.hospital.domain.Patient;
import capstone.hospital.repository.AdminRepository;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.NurseRepository;
import capstone.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final AdminRepository adminRepository;

    public Object login(String loginId, String loginPw) {

        Object fineMember = patientRepository.findByLoginId(loginId)
                .filter(patient -> patient.getLoginPw().equals(loginPw))
                .orElse(null);
        if (fineMember == null) {
            fineMember = doctorRepository.findByLoginId(loginId)
                    .filter(patient -> patient.getLoginPw().equals(loginPw))
                    .orElse(null);
        }
        if (fineMember == null) {
            fineMember = nurseRepository.findByLoginId(loginId)
                    .filter(patient -> patient.getLoginPw().equals(loginPw))
                    .orElse(null);
        }
        if (fineMember == null) {
            fineMember = adminRepository.findByLoginId(loginId)
                    .filter(patient -> patient.getLoginPw().equals(loginPw))
                    .orElse(null);
        }

        return fineMember;
    }

    @Transactional
    public void changePw(Long memberId, String oldPw, String newPw) {
        Patient findPatient = patientRepository.findById(memberId).get();
        findPatient.changePw(oldPw, newPw);
    }
}
