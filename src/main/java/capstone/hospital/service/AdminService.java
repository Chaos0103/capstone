package capstone.hospital.service;

import capstone.hospital.domain.Admin;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Nurse;
import capstone.hospital.dto.DoctorInfoDto;
import capstone.hospital.dto.NurseInfoDto;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.NurseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {

    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;

    /**
     * 승인된 의사 조회
     */
    public List<DoctorInfoDto> readDoctor(String name) {
        List<DoctorInfoDto> data = new ArrayList<>();
        List<Doctor> doctors = doctorRepository.approveDoctor(name);
        for (Doctor doctor : doctors) {
            data.add(new DoctorInfoDto(doctor));
        }
        return data;
    }

    /**
     * 승인 대기 의사 조회
     */
    public List<DoctorInfoDto> waitDoctor(String name, int startIndex, int pageSize) {
        List<DoctorInfoDto> data = new ArrayList<>();
        List<Doctor> doctors = doctorRepository.notApproveDoctor(name, startIndex, pageSize);
        for (Doctor doctor : doctors) {
            data.add(new DoctorInfoDto(doctor));
        }
        return data;
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

    public int totalCnt(String name) {
        return (int) doctorRepository.notApproveDoctorCnt(name);
    }

    /**
     * 승인된 간호사 조회
     */
    public List<NurseInfoDto> readNurse(String name) {
        List<NurseInfoDto> data = new ArrayList<>();
        List<Nurse> nurses = nurseRepository.approveNurse(name);
        for (Nurse nurse : nurses) {
            data.add(new NurseInfoDto(nurse));
        }
        return data;
    }

    /**
     * 승인 대기 간호사 조회
     */
    public List<NurseInfoDto> waitNurse(String name) {
        List<NurseInfoDto> data = new ArrayList<>();
        List<Nurse> nurses = nurseRepository.notApproveNurse(name);
        for (Nurse nurse : nurses) {
            data.add(new NurseInfoDto(nurse));
        }
        return data;
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
