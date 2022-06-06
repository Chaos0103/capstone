package capstone.hospital.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Patient patient;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "kcdcode_id")
    private KCDCode kcdCode;

    private LocalDateTime date;
    private String content;

    @OneToMany(mappedBy = "report")
    private List<Prescription> prescriptions = new ArrayList<>();

    //== 생성 메서드 ==//
    public Report(Patient patient, Doctor doctor, KCDCode kcdCode, String content) {
        this.patient = patient;
        this.doctor = doctor;
        this.kcdCode = kcdCode;
        this.content = content;
        this.date = LocalDateTime.now();
    }
}
