package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.BloodType;
import capstone.hospital.domain.enumtype.HospitalizationStatus;
import capstone.hospital.domain.enumtype.WardType;
import capstone.hospital.domain.valuetype.Ward;
import capstone.hospital.dto.CreateInpatientInfoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inpatient extends TimeBaseEntity {
    
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
    private String height;
    private String weight;

    @Embedded
    private Ward ward;

    @Enumerated(EnumType.STRING)
    private HospitalizationStatus status;

    public Inpatient(Patient patient, Doctor doctor, Report report) {
        this.patient = patient;
        this.doctor = doctor;
        this.report = report;
        this.ward = new Ward();
        this.status = HospitalizationStatus.WAIT;
    }

    //== 비즈니스 로직==//
    public void approve(CreateInpatientInfoDto data) {
        this.bloodType = data.getBloodType();
        this.height = data.getHeight();
        this.weight = data.getWeight();
        this.ward = new Ward(data.getWardType(), data.getRoomNumber(), data.getBedNumber());
        this.status = HospitalizationStatus.Hospitalization;
    }

    public void out() {
        this.status = HospitalizationStatus.OUT;
    }
}
