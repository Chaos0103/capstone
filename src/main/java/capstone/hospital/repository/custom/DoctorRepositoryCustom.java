package capstone.hospital.repository.custom;

import capstone.hospital.controller.patient.form.SearchForm;
import capstone.hospital.domain.Doctor;

import java.util.List;

public interface DoctorRepositoryCustom {
    List<String> findLoginId(String name, String phoneNumber);

    List<String> findLoginPw(String name, String phoneNumber, String loginId);

    List<Doctor> searchByDoctor(SearchForm form);

    List<Doctor> notApproveDoctor(String name, int startIndex, int pageSize);

    int notApproveDoctorCnt(String name);

    List<Doctor> approveDoctor(String name);
}
