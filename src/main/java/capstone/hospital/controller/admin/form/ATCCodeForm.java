package capstone.hospital.controller.admin.form;

import capstone.hospital.domain.enumtype.ATCType;
import lombok.Data;

@Data
public class ATCCodeForm {

    private String code;
    private String name;
    private String company;
    private ATCType type;
    private int stock;

}
