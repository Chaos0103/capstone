package capstone.hospital.service;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.DoctorEsl;
import capstone.hospital.dto.DoctorEslDto;
import capstone.hospital.dto.DoctorInfoDto;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.DoctorEslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorEslService {

    private final DoctorEslRepository doctorEslRepository;
    private final DoctorRepository doctorRepository;

    public List<DoctorEslDto> list(String name) {
        List<DoctorEslDto> data = new ArrayList<>();
        List<DoctorEsl> find = doctorEslRepository.findByName(name);
        for (DoctorEsl doctorEsl : find) {
            data.add(new DoctorEslDto(doctorEsl));
        }
        return data;
    }

    public DoctorInfoDto findDoctor(String name) {
        Optional<Doctor> doctor = doctorRepository.findByInfoName(name);
        if (doctor.isPresent()) {
            return new DoctorInfoDto(doctor.get());
        } else {
            return new DoctorInfoDto();
        }
    }

    public DoctorEslDto findDoctorById(Long id) {
        Optional<DoctorEsl> esl = doctorEslRepository.findById(id);
        return new DoctorEslDto(esl.get());
    }

    @Transactional
    public void save(String name, String room) {
        Optional<Doctor> doctor = doctorRepository.findByInfoName(name);
        doctorEslRepository.save(new DoctorEsl(doctor.get(), room));
    }

    @Transactional
    public void delete(Long id) {
        doctorEslRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String name, String room) {
        Optional<DoctorEsl> esl = doctorEslRepository.findById(id);
        DoctorEsl doctorEsl = esl.get();
        Optional<Doctor> doctor = doctorRepository.findByInfoName(name);
        doctorEsl.update(doctor.get(), room);
    }
}
