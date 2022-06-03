package capstone.hospital.dto;

import capstone.hospital.domain.Inpatient;
import capstone.hospital.domain.enumtype.BloodType;
import lombok.Data;

@Data
public class InpatientInfoDto {

    private Long id;

    private String patientName;
    private String sex;
    private String phoneNumber;
    private String rrn;

    private String doctorName;
    private String major;
    private String address;

    private BloodType bloodType;

    private String wardType;
    private String roomNumber;
    private String bedNumber;

    private String height;
    private String weight;

    private String diseaseName;

    public InpatientInfoDto() {}

    public InpatientInfoDto(Inpatient data) {
        this.id = data.getId();

        this.major = data.getDoctor().getMajor().getDescription();
        this.patientName = data.getPatient().getInfo().getName();
        this.sex = data.getPatient().getInfo().getSex().getDescription();
        this.phoneNumber = data.getPatient().getInfo().getPhoneNumber();
        this.rrn = data.getPatient().getInfo().getRrn();
        this.doctorName = data.getDoctor().getInfo().getName();
        this.bloodType = data.getBloodType();
        this.wardType = data.getWard().getWardType().getDescription();
        this.roomNumber = data.getWard().getRoomNumber();
        this.bedNumber = data.getWard().getBedNumber();
        this.address = "(" + data.getPatient().getInfo().getAddress().getZipcode() + ") "
                + data.getPatient().getInfo().getAddress().getMainAddress() + " "
                + data.getPatient().getInfo().getAddress().getSubAddress();
        this.height = data.getHeight();
        this.weight = data.getWeight();
        this.diseaseName = data.getReport().getKcdCode().getName();
    }
}
