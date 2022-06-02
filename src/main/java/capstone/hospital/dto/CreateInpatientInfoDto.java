package capstone.hospital.dto;

import capstone.hospital.controller.nurse.form.InpatientForm;
import capstone.hospital.domain.enumtype.BloodType;
import capstone.hospital.domain.enumtype.WardType;
import lombok.Data;

@Data
public class CreateInpatientInfoDto {

    private BloodType bloodType;
    private String height;
    private String weight;

    private WardType wardType;
    private String roomNumber;
    private String bedNumber;

    public CreateInpatientInfoDto(InpatientForm data) {
        this.bloodType = data.getBloodType();
        this.height = data.getHeight();
        this.weight = data.getWeight();
        this.wardType = data.getWardType();
        this.roomNumber = data.getRoomNumber();
        this.bedNumber = data.getBedNumber();
    }
}
