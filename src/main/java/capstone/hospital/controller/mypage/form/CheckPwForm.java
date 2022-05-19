package capstone.hospital.controller.mypage.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CheckPwForm {

    @NotBlank
    private String password;
}
