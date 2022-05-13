package capstone.hospital.service;

import capstone.hospital.controller.patient.form.SearchForm;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.repository.DoctorQueryRepository;
import capstone.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final DoctorRepository doctorRepository;
    private final DoctorQueryRepository doctorQueryRepository;

    public List<Doctor> doctorSearch(SearchForm form) {
        return doctorQueryRepository.searchByDoctor(form);
    }

    public List<Doctor> doctorSearchByMajor(Major major) {
        return doctorRepository.findByMajor(major);
    }
}
