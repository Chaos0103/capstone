package capstone.hospital.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PhoneCheckForm {

    @NotBlank
    private String phoneNumberFront;
    @NotBlank
    private String phoneNumberMid;
    @NotBlank
    private String phoneNumberBack;
//    @NotBlank
    private String passNumber = "0";
}
