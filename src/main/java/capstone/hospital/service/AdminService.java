package capstone.hospital.service;

import capstone.hospital.domain.Doctor;
import capstone.hospital.repository.DoctorQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {

    private final DoctorQueryRepository doctorQueryRepository;

    /**
     * 승인된 의사 조회
     */
    public List<Doctor> readDoctor() {
        return doctorQueryRepository.approvedDoctor();
    }

    /**
     * 승인 대기 의사 조회
     */
    public List<Doctor> waitDoctor() {
        return doctorQueryRepository.waitDoctor();
    }

}
