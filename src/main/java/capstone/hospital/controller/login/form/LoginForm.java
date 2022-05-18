package capstone.hospital.controller.login.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {

    @NotBlank
    private String loginId;

    @NotBlank
    private String loginPw;
}
