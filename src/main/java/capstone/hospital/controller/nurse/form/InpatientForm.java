package capstone.hospital.controller.nurse.form;

import capstone.hospital.domain.enumtype.BloodType;
import capstone.hospital.domain.enumtype.WardType;
import lombok.Data;

@Data
public class InpatientForm {

    private BloodType bloodType;
    private String height;
    private String weight;

    private WardType wardType;
    private String roomNumber;
    private String bedNumber;

}
