package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.MedicalStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Medical extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "medical_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String medicalDay;
    private String medicalTime;

    @Enumerated(EnumType.STRING)
    private MedicalStatus status;

    //== 생성 메서드 ==//
    public Medical(Doctor doctor, String medicalDay, String medicalTime) {
        this.doctor = doctor;
        this.medicalDay = medicalDay;
        this.medicalTime = medicalTime;
        this.status = MedicalStatus.POSSIBLE;
    }

    //== 비즈니스 로직 ==//
    public void success() {
        this.status = MedicalStatus.IMPOSSIBLE;
    }

    public void cancel() {
        this.status = MedicalStatus.POSSIBLE;
    }

}
