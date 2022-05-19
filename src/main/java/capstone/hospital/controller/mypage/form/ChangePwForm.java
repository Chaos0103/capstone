package capstone.hospital.controller.mypage.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePwForm {

    @NotBlank
    private String nowPw;
    @NotBlank
    private String newPw;
    @NotBlank
    private String checkNewPw;
}
