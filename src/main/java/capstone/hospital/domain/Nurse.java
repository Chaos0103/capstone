package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.NurseRank;
import capstone.hospital.domain.enumtype.WardType;
import capstone.hospital.domain.valuetype.Information;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
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
    private WardType majorWard;

    @Enumerated(EnumType.STRING)
    private NurseRank rank;
}
