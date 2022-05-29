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

    private String singleDose;
    private String dailyDose;
    private String totalDoseDays;

    //== 생성 메서드 ==//
    public Prescription(ATCCode atcCode, Report report, String singleDose, String dailyDose, String totalDoseDays) {
        this.atcCode = atcCode;
        this.report = report;
        this.singleDose = singleDose;
        this.dailyDose = dailyDose;
        this.totalDoseDays = totalDoseDays;
    }

    public Prescription() {

    }

    //== 비즈니스 로직 ==//
    public void addPrescription() {
        report.getPrescriptions().add(this);
    }
}
