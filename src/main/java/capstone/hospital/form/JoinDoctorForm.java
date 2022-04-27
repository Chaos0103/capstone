package capstone.hospital.form;

import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JoinDoctorForm {

    @NotBlank
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

    private String licenseCode;

    private Major major;
    private DoctorRank rank;
}
