package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.dto.UploadFile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doctor {

    @Id @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "admin_id")
    private Admin approvalAdmin;

    @Column(length = 20, updatable = false)
    private String loginId;
    @Column(length = 20)
    private String loginPw;

    private String university;

    @Embedded
    private Information info;

    @Column(updatable = false)
    private String licenseCode;

    @Enumerated(EnumType.STRING)
    private Major major;
    @Enumerated(EnumType.STRING)
    private DoctorRank rank;

    @OneToMany(mappedBy = "doctor")
    private List<Inpatient> inpatients = new ArrayList<>();

    private UploadFile profile;

    public Doctor(String loginId, String loginPw, String university, Information info, String licenseCode, Major major, DoctorRank rank, UploadFile profile) {
        this.approvalAdmin = null;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.university = university;
        this.info = info;
        this.licenseCode = licenseCode;
        this.major = major;
        this.rank = rank;
        this.profile = profile;
    }

    //==비즈니스 로직==//

    /**
     * 승인
     */
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
