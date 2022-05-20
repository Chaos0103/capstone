package capstone.hospital.controller.mypage.form;

import capstone.hospital.dto.type.MemberType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InfoForm {

    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String zipcode;

    private String memberType;
}
