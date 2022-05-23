package capstone.hospital.service;

import capstone.hospital.controller.patient.form.SearchForm;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> doctorSearch(SearchForm form) {
        return doctorRepository.searchByDoctor(form);
    }

    public List<Doctor> doctorSearchByMajor(Major major) {
        return doctorRepository.findByMajor(major);
    }

    public Optional<Doctor> doctorSearchById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }
}
