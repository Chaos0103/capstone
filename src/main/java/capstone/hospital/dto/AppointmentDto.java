package capstone.hospital.dto;

import capstone.hospital.domain.Appointment;
import capstone.hospital.domain.enumtype.SexType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDto {

    private Long id;
    private String name;
    private String sex;
    private int age;
    private String phoneNumber;
    private String appointmentDate;
    private String appointmentTime;

    public AppointmentDto(Appointment data) {
        this.id = data.getId();
        this.name = data.getPatient().getInfo().getName();
        this.sex = getSex(data.getPatient().getInfo().getSex());
        this.age = getAge(data.getPatient().getInfo().getRrn());
        this.phoneNumber = data.getPatient().getInfo().getPhoneNumber();
        this.appointmentDate = data.getMedicalDate();
        this.appointmentTime = data.getMedicalTime();
    }

    private int getAge(String rrn) {
        int now = LocalDateTime.now().getYear();
        int year = Integer.parseInt(rrn.substring(0, 1));
        if (0 <= year && year <= (now % 100)) {
            year += (now / 100) * 100;
        } else {
            year += (now / 100 - 1) * 100;
        }
        return now - year;
    }

    private String getSex(SexType sex) {
        if (sex.equals(SexType.MALE)) {
            return "M";
        } else {
            return "F";
        }
    }
}
