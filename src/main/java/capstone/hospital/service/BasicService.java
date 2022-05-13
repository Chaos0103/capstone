package capstone.hospital.service;

import capstone.hospital.domain.Patient;
import capstone.hospital.dto.InformationDto;
import capstone.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicService {

    private final PatientRepository patientRepository;

    @Transactional
    public void changeMemberInfo(Long memberId ,InformationDto info) {
        Optional<Patient> findMember = patientRepository.findById(memberId);
        findMember.get().changeInfo(info);
    }

    @Transactional
    public void changePw(Long memberId, String oldPw, String newPw) {
        Patient findPatient = patientRepository.findById(memberId).get();
        findPatient.changePw(oldPw, newPw);
    }

    @Transactional
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
