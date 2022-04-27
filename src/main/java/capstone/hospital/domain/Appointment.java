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
    @JoinColumn(name = "medical_id")
    private Medical medical;

    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    //== 생성 메서드 ==//
    public Appointment(Patient patient, Medical medical) {
        this.patient = patient;
        this.medical = medical;
        this.appointmentDate = LocalDateTime.now();
        this.status = AppointmentStatus.APPOINTMENT;
    }

    //== 비즈니스 로직==//
    public void cancel() {
        this.status = AppointmentStatus.CANCEL;
    }

    public void updateAppointment(Medical medical) {
        this.medical = medical;
    }
}
