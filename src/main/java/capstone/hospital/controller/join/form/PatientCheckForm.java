package capstone.hospital.controller.join.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PatientCheckForm {

    @NotBlank
    private String phoneNumberFront;
    @NotBlank
    private String phoneNumberMid;
    @NotBlank
    private String phoneNumberBack;

    private String checkNumber = "none";
    private String inputNumber;

    private boolean onOff = false;

    public PatientCheckForm(String phoneNumberFront, String phoneNumberMid, String phoneNumberBack, String checkNumber) {
        this.phoneNumberFront = phoneNumberFront;
        this.phoneNumberMid = phoneNumberMid;
        this.phoneNumberBack = phoneNumberBack;
        this.checkNumber = checkNumber;
    }
}
