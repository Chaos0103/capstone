package capstone.hospital.service;

import capstone.hospital.domain.Admin;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Nurse;
import capstone.hospital.repository.DoctorQueryRepository;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.NurseQueryRepository;
import capstone.hospital.repository.NurseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {

    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final DoctorQueryRepository doctorQueryRepository;
    private final NurseQueryRepository nurseQueryRepository;

    /**
     * 승인된 의사 조회
     */
    public List<Doctor> readDoctor(String name) {
        return doctorQueryRepository.approveDoctor(name);
    }

    /**
     * 승인 대기 의사 조회
     */
    public List<Doctor> waitDoctor(String name) {
        return doctorQueryRepository.notApproveDoctor(name);
    }

    /**
     * 의사 승인
     */
    @Transactional
    public void approveDoctor(Long doctorId, Admin admin) {
        Optional<Doctor> findDoctor = doctorRepository.findById(doctorId);
        if (findDoctor.isPresent()) {
            Doctor doctor = findDoctor.get();
            doctor.approve(admin);
        }
    }

    /**
     * 의사 삭제
     */
    @Transactional
    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    /**
     * 승인된 간호사 조회
     */
    public List<Nurse> readNurse(String name) {
        return nurseQueryRepository.approveNurse(name);
    }

    /**
     * 승인 대기 간호사 조회
     */
    public List<Nurse> waitNurse(String name) {
        return nurseQueryRepository.notApproveNurse(name);
    }

    /**
     * 간호사 승인
     */
    @Transactional
    public void approveNurse(Long nurseId, Admin admin) {
        Optional<Nurse> findNurse = nurseRepository.findById(nurseId);
        if (findNurse.isPresent()) {
            Nurse nurse = findNurse.get();
            nurse.approve(admin);
        }
    }

    /**
     * 간호사 삭제
     */
    @Transactional
    public void deleteNurse(Long nurseId) {
        nurseRepository.deleteById(nurseId);
    }
}
