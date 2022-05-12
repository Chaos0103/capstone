package capstone.hospital.service;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Medical;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.MedicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalService {

    private final DoctorRepository doctorRepository;
    private final MedicalRepository medicalRepository;

    /**
     * 진료 등록
     */
    public Long createMedical(Long doctorId, String day, String time) {
        Optional<Doctor> findDoctor = doctorRepository.findById(doctorId);
        if (findDoctor.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 의사입니다.");
        } else {
            Medical medical = new Medical(findDoctor.get(), day, time);
            return medical.getId();
        }
    }

    /**
     * 업데이트
     */
    public void updateMedical(Long doctorId, Long medicalId) {
        Optional<Doctor> findDoctor = doctorRepository.findById(doctorId);
        Optional<Medical> findMedical = medicalRepository.findById(medicalId);
        if (findMedical.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 진료입니다.");
        } else {
            // updateForm input
        }
    }
}
