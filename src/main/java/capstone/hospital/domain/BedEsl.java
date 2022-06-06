package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.BloodType;
import capstone.hospital.domain.enumtype.SexType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BedEsl {

    @Id
    @GeneratedValue
    @Column(name = "bed_esl_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "inpatient_id")
    private Inpatient inpatient;
}
