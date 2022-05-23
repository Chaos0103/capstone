package capstone.hospital.dto;

import capstone.hospital.controller.admin.form.EslForm;
import capstone.hospital.domain.DoctorEsl;
import lombok.Data;

@Data
public class DoctorEslDto {

    private Long id;
    private String name;
    private String major;
    private String rank;
    private String university;
    private String phoneNumber;
    private String room;

    public DoctorEslDto(DoctorEsl doctorEsl) {
        this.id = doctorEsl.getId();
        this.name = doctorEsl.getDoctor().getInfo().getName();
        this.major = doctorEsl.getDoctor().getMajor().getDescription();
        this.rank = doctorEsl.getDoctor().getRank().getDescription();
        this.university = doctorEsl.getDoctor().getUniversity();
        this.phoneNumber = doctorEsl.getDoctor().getInfo().getPhoneNumber();
        this.room = doctorEsl.getRoomInfo();
    }

    public DoctorEslDto(EslForm form) {
        this.name = form.getName();
        this.major = form.getMajor();
        this.rank = form.getRank();
        this.university = form.getUniversity();
        this.phoneNumber = form.getPhoneNumber();
    }
}
