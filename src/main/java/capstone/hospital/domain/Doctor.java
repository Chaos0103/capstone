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

    private String loginId;
    private String loginPw;

    @Embedded
    private Information info;
    private String licenseCode;

    @Enumerated(EnumType.STRING)
    private Major major;
    @Enumerated(EnumType.STRING)
    private DoctorRank rank;

    @OneToMany(mappedBy = "doctor")
    private List<Inpatient> inpatients = new ArrayList<>();

//    private UploadFile profile;



    public Doctor(String loginId, String loginPw, Information info, String licenseCode, Major major, DoctorRank rank) {
        this.approvalAdmin = null;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.info = info;
        this.licenseCode = licenseCode;
        this.major = major;
        this.rank = rank;
//        this.profile = profile;
    }
}
