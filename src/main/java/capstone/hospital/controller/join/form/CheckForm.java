package capstone.hospital.controller.join.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CheckForm {

    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String rrnFront;
    @NotBlank
    private String rrnBack;

    private String rrn;
    private String checkNumber = "none";
    private String inputNumber;

    public CheckForm(String name, String phoneNumber, String rrnFront, String rrnBack, String checkNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rrnFront = rrnFront;
        this.rrnBack = rrnBack;
        this.rrn = rrnFront + "-" + rrnBack;
        this.checkNumber = checkNumber;
    }
}
