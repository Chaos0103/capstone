package capstone.hospital.service;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Medical;
import capstone.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalService {

    private final DoctorRepository doctorRepository;

    public Long createMedical(Long doctorId, String day, String time) {
        Optional<Doctor> findDoctor = doctorRepository.findById(doctorId);
        if (findDoctor.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 의사입니다.");
        } else {
            Medical medical = new Medical(findDoctor.get(), day, time);
            return medical.getId();
        }
    }
}
