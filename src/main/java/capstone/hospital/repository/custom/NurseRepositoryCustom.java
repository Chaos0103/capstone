package capstone.hospital.repository.custom;

import capstone.hospital.domain.Nurse;

import java.util.List;

public interface NurseRepositoryCustom {
    List<String> findLoginId(String name, String phoneNumber);

    List<String> findLoginPw(String name, String phoneNumber, String loginId);

    List<Nurse> approveNurse(String name);

    List<Nurse> notApproveNurse(String name);
}
