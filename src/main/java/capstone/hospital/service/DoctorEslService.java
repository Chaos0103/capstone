package capstone.hospital.service;

import capstone.hospital.domain.DoctorEsl;
import capstone.hospital.repository.DoctorEslQueryRepository;
import capstone.hospital.repository.DoctorEslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorEslService {

    private final DoctorEslRepository doctorEslRepository;
    private final DoctorEslQueryRepository doctorEslQueryRepository;


    public List<DoctorEsl> list(String name) {
        return doctorEslQueryRepository.findByName(name);
    }
}
