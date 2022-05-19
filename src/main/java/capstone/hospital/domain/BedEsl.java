package capstone.hospital.domain;

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

//    private Doctor doctor;
}
