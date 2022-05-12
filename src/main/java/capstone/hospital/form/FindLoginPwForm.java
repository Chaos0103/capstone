package capstone.hospital.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindLoginPwForm {

    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumberFront;
    @NotBlank
    private String phoneNumberMid;
    @NotBlank
    private String phoneNumberBack;
    @NotBlank
    private String loginId;

    private String phoneNumber;
    private String checkNumber;
    private String inputNumber;

    public FindLoginPwForm(String name, String phoneNumberFront, String phoneNumberMid, String phoneNumberBack, String loginId, String checkNumber) {
        this.name = name;
        this.phoneNumberFront = phoneNumberFront;
        this.phoneNumberMid = phoneNumberMid;
        this.phoneNumberBack = phoneNumberBack;
        this.loginId = loginId;
        this.checkNumber = checkNumber;
    }
}
