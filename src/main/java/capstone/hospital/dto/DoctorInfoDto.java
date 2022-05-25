package capstone.hospital.dto;

import capstone.hospital.domain.Doctor;
import lombok.Data;

@Data
public class DoctorInfoDto {

    private Long id;
    private String code;
    private String name;
    private String sex;
    private String phoneNumber;
    private String major;
    private String rank;
    private String university;
    private UploadFile file;

    public DoctorInfoDto() {

    }

    public DoctorInfoDto(Doctor doctor) {
        this.id = doctor.getId();
        this.code = doctor.getLicenseCode();
        this.name = doctor.getInfo().getName();
        this.sex = doctor.getInfo().getSex().getDescription();
        this.phoneNumber = doctor.getInfo().getPhoneNumber();
        this.major = doctor.getMajor().getDescription();
        this.rank = doctor.getRank().getDescription();
        this.university = doctor.getUniversity();
        this.file = doctor.getProfile();
    }
}
