package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.AppointmentStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Appointment {

    @Id
    @GeneratedValue
    @Column(name = "appointment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Patient patient;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(length = 10)
    private String medicalDate;
    @Column(length = 5)
    private String medicalTime;

    //== 생성 메서드 ==//
    public Appointment(Patient patient, Doctor doctor, String medicalDate, String medicalTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.status = AppointmentStatus.APPOINTMENT;
        this.medicalDate = medicalDate;
        this.medicalTime = medicalTime;
        this.appointmentDate = LocalDateTime.now();
    }

    //== 비즈니스 로직==//
    public void cancel() {
        this.status = AppointmentStatus.CANCEL;
    }

    public void update(Doctor doctor, String medicalDate, String medicalTime) {
        this.doctor = doctor;
        this.medicalDate = medicalDate;
        this.medicalTime = medicalTime;
        this.appointmentDate = LocalDateTime.now();
    }
}
