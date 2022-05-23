package capstone.hospital.controller.admin.form;

import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.dto.DoctorEslDto;
import capstone.hospital.dto.DoctorInfoDto;
import lombok.Data;

@Data
public class EslForm {

    private String name;
    private String major;
    private String rank;
    private String university;
    private String phoneNumber;
    private String room;
    // QR

    public void input(DoctorInfoDto data) {
        this.name = data.getName();
        this.major = data.getMajor();
        this.rank = data.getRank();
        this.university = data.getUniversity();
        this.phoneNumber = data.getPhoneNumber();
    }

    public void updateInit(DoctorEslDto data) {
        this.name = data.getName();
        this.major = data.getMajor();
        this.rank = data.getRank();
        this.university = data.getUniversity();
        this.phoneNumber = data.getPhoneNumber();
        this.room = data.getRoom();
    }
}
