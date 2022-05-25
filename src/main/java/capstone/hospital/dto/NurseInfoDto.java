package capstone.hospital.dto;

import capstone.hospital.domain.Nurse;
import lombok.Data;

@Data
public class NurseInfoDto {

    private Long id;
    private String code;
    private String name;
    private String sex;
    private String phoneNumber;
    private String major;
    private UploadFile file;

    public NurseInfoDto(Nurse nurse) {
        this.id = nurse.getId();
        this.code = nurse.getLicenseCode();
        this.name = nurse.getInfo().getName();
        this.sex = nurse.getInfo().getSex().getDescription();
        this.phoneNumber = nurse.getInfo().getPhoneNumber();
        this.major = nurse.getMajor().getDescription();
        this.file = nurse.getProfile();
    }
}
