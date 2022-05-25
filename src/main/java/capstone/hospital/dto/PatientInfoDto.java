package capstone.hospital.dto;

import capstone.hospital.domain.Patient;
import lombok.Data;

@Data
public class PatientInfoDto {

    private Long id;
    private String name;
    private String sex;
    private String phoneNumber;
    private String rrn;
    private String address;

    public PatientInfoDto() {
    }

    public PatientInfoDto(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getInfo().getName();
        this.sex = patient.getInfo().getSex().getDescription();
        this.phoneNumber = patient.getInfo().getPhoneNumber();
        this.rrn = patient.getInfo().getRrn();
        this.address = "(" + patient.getInfo().getAddress().getZipcode() + ") "
                + patient.getInfo().getAddress().getMainAddress() + " "
                + patient.getInfo().getAddress().getSubAddress();
    }
}
