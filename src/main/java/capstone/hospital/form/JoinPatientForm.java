package capstone.hospital.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JoinPatientForm {

    @NotBlank
    private String loginId;
    @NotBlank
    private String loginPw;
    @NotBlank
    private String checkPw;
    @NotBlank
    private String name;
    @NotBlank
    private String rrnFront;
    @NotBlank
    private String rrnBack;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String zipcode;
}
