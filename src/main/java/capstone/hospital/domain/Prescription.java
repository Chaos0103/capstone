package capstone.hospital.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Prescription {

    @Id @GeneratedValue
    @Column(name = "prescription_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "atccode_id")
    private ATCCode atcCode;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    private int singleDose;
    private int dailyDose;
    private int totalDoseDays;
}
