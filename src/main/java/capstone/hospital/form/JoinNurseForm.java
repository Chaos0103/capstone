package capstone.hospital.form;

import capstone.hospital.domain.enumtype.Major;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JoinNurseForm {


    private String loginId;
    @NotBlank
    private String loginPw;
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
    @NotBlank
    private String licenseCode;
//    @NotBlank
    private Major major;

    @NotBlank
    private String checkPw;
}
