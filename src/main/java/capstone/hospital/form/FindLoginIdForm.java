package capstone.hospital.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindLoginIdForm {

    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumberFront;
    @NotBlank
    private String phoneNumberMid;
    @NotBlank
    private String phoneNumberBack;

    private String phoneNumber;
    private String checkNumber;
    private String inputNumber;

    public FindLoginIdForm(String name, String phoneNumberFront, String phoneNumberMid, String phoneNumberBack, String checkNumber) {
        this.name = name;
        this.phoneNumberFront = phoneNumberFront;
        this.phoneNumberMid = phoneNumberMid;
        this.phoneNumberBack = phoneNumberBack;
        this.checkNumber = checkNumber;
    }
}
