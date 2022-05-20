package capstone.hospital.service;

import capstone.hospital.domain.Admin;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Nurse;
import capstone.hospital.domain.Patient;
import capstone.hospital.dto.InfoDto;
import capstone.hospital.dto.type.MemberType;
import capstone.hospital.repository.AdminRepository;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.NurseRepository;
import capstone.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static capstone.hospital.dto.type.MemberType.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public void changeMemberInfo(Object loginMember , InfoDto info) {
        if (patientEq(loginMember)) {
            Patient patient = patientRepository.findById(((Patient) loginMember).getId()).get();
            patient.changeInfo(info);
        }
    }

    @Transactional
    public void changePw(Object loginMember, String oldPw, String newPw) {
        if (patientEq(loginMember)) {
            Patient patient = patientRepository.findById(((Patient) loginMember).getId()).get();
            patient.changePw(oldPw, newPw);
        } else if (doctorEq(loginMember)) {
            Doctor doctor = doctorRepository.findById(((Doctor) loginMember).getId()).get();
            doctor.changePw(oldPw, newPw);
        } else if (nurseEq(loginMember)) {
            Nurse nurse = nurseRepository.findById(((Nurse) loginMember).getId()).get();
            nurse.changePw(oldPw, newPw);
        } else if (adminEq(loginMember)) {
            Admin admin = adminRepository.findById(((Admin) loginMember).getId()).get();
            admin.changePw(oldPw, newPw);
        } else {
            throw new IllegalStateException("Unexpected value: " + loginMember.getClass());
        }
    }

    @Transactional
    public void delete(Object loginMember, String pw) {
        if (patientEq(loginMember) && ((Patient) loginMember).getLoginPw().equals(pw)) {
            patientRepository.deleteById(((Patient) loginMember).getId());
        } else if (doctorEq(loginMember) && ((Doctor) loginMember).getLoginPw().equals(pw)) {
            doctorRepository.deleteById(((Doctor) loginMember).getId());
        } else if (nurseEq(loginMember) && ((Nurse) loginMember).getLoginPw().equals(pw)) {
            nurseRepository.deleteById(((Nurse) loginMember).getId());
        } else if (adminEq(loginMember) && ((Admin) loginMember).getLoginPw().equals(pw)) {
            adminRepository.deleteById(((Admin) loginMember).getId());
        } else {
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }
    }

    public InfoDto findInfo(Object loginMember) {
        if (patientEq(loginMember)) {
            Patient patient = patientRepository.findById(((Patient) loginMember).getId()).get();
            return new InfoDto(patient.getInfo().getPhoneNumber(), patient.getInfo().getAddress().getCity(),
                    patient.getInfo().getAddress().getStreet(), patient.getInfo().getAddress().getZipcode(),
                    PATIENT);
        } else if (doctorEq(loginMember)) {
            Doctor doctor = doctorRepository.findById(((Doctor) loginMember).getId()).get();
            return new InfoDto(doctor.getInfo().getPhoneNumber(), doctor.getInfo().getAddress().getCity(),
                    doctor.getInfo().getAddress().getStreet(), doctor.getInfo().getAddress().getZipcode(),
                    DOCTOR);
        } else if (nurseEq(loginMember)) {
            Nurse nurse = nurseRepository.findById(((Nurse) loginMember).getId()).get();
            return new InfoDto(nurse.getInfo().getPhoneNumber(), nurse.getInfo().getAddress().getCity(),
                    nurse.getInfo().getAddress().getStreet(), nurse.getInfo().getAddress().getZipcode(),
                    NURSE);
        } else if (adminEq(loginMember)) {
            Admin admin = adminRepository.findById(((Admin) loginMember).getId()).get();
            return new InfoDto(admin.getInfo().getPhoneNumber(), admin.getInfo().getAddress().getCity(),
                    admin.getInfo().getAddress().getStreet(), admin.getInfo().getAddress().getZipcode(),
                    ADMIN);
        } else {
            throw new IllegalStateException("Unexpected value: " + loginMember.getClass());
        }
    }

    private boolean adminEq(Object loginMember) {
        return Admin.class.equals(loginMember.getClass());
    }

    private boolean nurseEq(Object loginMember) {
        return Nurse.class.equals(loginMember.getClass());
    }

    private boolean doctorEq(Object loginMember) {
        return Doctor.class.equals(loginMember.getClass());
    }

    private boolean patientEq(Object loginMember) {
        return Patient.class.equals(loginMember.getClass());
    }
}
