package capstone.hospital.dto;

import capstone.hospital.domain.Doctor;
import lombok.Data;

@Data
public class DoctorInfoDto {

    private String name;
    private String major;
    private String rank;
    private String university;
    private String phoneNumber;

    public DoctorInfoDto() {

    }

    public DoctorInfoDto(Doctor doctor) {
        this.name = doctor.getInfo().getName();
        this.major = doctor.getMajor().getDescription();
        this.rank = doctor.getRank().getDescription();
        this.university = doctor.getUniversity();
        this.phoneNumber = doctor.getInfo().getPhoneNumber();
    }
}
