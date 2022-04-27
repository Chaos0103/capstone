package capstone.hospital.service;

import capstone.hospital.domain.Patient;
import capstone.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final PatientRepository patientRepository;

    /**
     * 로그인 서비스
     * @param loginId
     * @param loginPw
     * @return
     */
    public Patient login(String loginId, String loginPw) {
        return patientRepository.findByLoginId(loginId)
                .filter(patient -> patient.getLoginPw().equals(loginPw))
                .orElse(null);
    }

    @Transactional
    public void changePw(Long memberId, String oldPw, String newPw) {
        Patient findPatient = patientRepository.findById(memberId).get();
        findPatient.changePw(oldPw, newPw);
    }
}
