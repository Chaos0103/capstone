package capstone.hospital.controller.admin.form;

import capstone.hospital.domain.enumtype.ATCType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ATCCodeForm {

    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String company;
    @NotBlank
    private int stock;

    private ATCType type;
}
