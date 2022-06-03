package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.BloodType;
import capstone.hospital.domain.enumtype.SexType;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class BedEsl {

    @Id
    @GeneratedValue
    @Column(name = "bed_esl_id")
    private Long id;

    private String inpatientName;
    private SexType sex;
    private BloodType bloodType;

//    private Doctor doctor;
}
