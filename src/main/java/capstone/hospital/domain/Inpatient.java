package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.BloodType;
import capstone.hospital.domain.valuetype.Ward;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Inpatient extends BaseEntity {
    
    @Id @GeneratedValue
    @Column(name = "inpatient_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Patient patient;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Embedded
    private Ward ward;
}
