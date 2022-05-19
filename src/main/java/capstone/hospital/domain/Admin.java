package capstone.hospital.domain;

import capstone.hospital.domain.valuetype.Information;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {

    @Id @GeneratedValue
    @Column(name = "admin_id")
    private Long id;

    private String loginId;
    private String loginPw;

    @Embedded
    private Information info;

    public Admin(String loginId, String loginPw, Information info) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.info = info;
    }

    //== 비즈니스 로직 ==//
    public void changePw(String oldPw, String newPw) {
        if (loginPw.equals(oldPw)) {
            loginPw = newPw;
        } else {
            throw new IllegalStateException("현재 비밀번호가 다릅니다.");
        }
    }
}
