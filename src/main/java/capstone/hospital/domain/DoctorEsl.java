package capstone.hospital.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class DoctorEsl {

    @Id
    @GeneratedValue
    @Column(name = "doctor_esl_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String roomInfo;

    // 상태(휴가, 내래, 외래, 수술 등) 추가 고려

    public DoctorEsl() {
    }

    public DoctorEsl(Doctor doctor, String roomInfo) {
        this.doctor = doctor;
        this.roomInfo = roomInfo;
    }

    //== 비즈니스 로직 ==//
    public void update(Doctor doctor, String roomInfo) {
        this.doctor = doctor;
        this.roomInfo = roomInfo;
    }
}
