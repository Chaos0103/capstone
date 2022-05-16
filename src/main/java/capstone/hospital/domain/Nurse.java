package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.valuetype.Information;
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

    private String loginId;
    private String loginPw;

    @Embedded
    private Information info;
    private String licenseCode;

    @Enumerated(EnumType.STRING)
    private Major major;

    public Nurse(String loginId, String loginPw, Information info, String licenseCode, Major major) {
        this.approvalAdmin = null;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.info = info;
        this.licenseCode = licenseCode;
        this.major = major;
    }

    //==비즈니스 로직==//
    public void approve(Admin admin) {
        this.approvalAdmin = admin;
    }

}
