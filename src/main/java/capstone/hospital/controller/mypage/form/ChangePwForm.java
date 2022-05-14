package capstone.hospital.controller.mypage.form;

import lombok.Data;

@Data
public class ChangePwForm {

    private String nowPw;
    private String newPw;
    private String checkNewPw;
}
