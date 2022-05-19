package capstone.hospital.controller.admin.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class KCDCodeForm {

    @NotBlank
    public String code;
    @NotBlank
    public String name;
}
