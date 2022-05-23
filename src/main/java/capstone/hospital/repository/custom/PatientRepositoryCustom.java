package capstone.hospital.repository.custom;

import java.util.List;

public interface PatientRepositoryCustom {
    List<String> findLoginId(String name, String phoneNumber);

    List<String> findLoginPw(String name, String phoneNumber, String loginId);
}
