package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.dto.UploadFile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nurse {

    @Id @GeneratedValue
    @Column(name = "nurse_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "admin_id")
    private Admin approvalAdmin;

    @Column(length = 20, updatable = false)
    private String loginId;
    @Column(length = 20)
    private String loginPw;

    @Embedded
    private Information info;

    @Column(updatable = false)
    private String licenseCode;

    @Enumerated(EnumType.STRING)
    private Major major;

    private UploadFile profile;

    public Nurse(String loginId, String loginPw, Information info, String licenseCode, Major major, UploadFile profile) {
        this.approvalAdmin = null;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.info = info;
        this.licenseCode = licenseCode;
        this.major = major;
        this.profile = profile;
    }

    //==비즈니스 로직==//
    public void approve(Admin admin) {
        this.approvalAdmin = admin;
    }

    public void changePw(String oldPw, String newPw) {
        if (loginPw.equals(oldPw)) {
            loginPw = newPw;
        } else {
            throw new IllegalStateException("현재 비밀번호가 다릅니다.");
        }
    }
}
