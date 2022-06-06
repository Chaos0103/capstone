package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.ATCType;
import capstone.hospital.domain.enumtype.ChartStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chart {

    @Id @GeneratedValue
    @Column(name = "chart_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    private Prescription prescription;

    @Column(updatable = false)
    private LocalDateTime createdDate;
    private String createdBy;

    @Enumerated
    private ChartStatus status;

    public Chart(Prescription prescription, String createdBy) {
        this.prescription = prescription;
        this.createdDate = LocalDateTime.now();
        this.createdBy = createdBy;
        if (prescription.getAtcCode().getType() == ATCType.PILL) {
            this.status = ChartStatus.COMPLETE;
        } else if (prescription.getAtcCode().getType() == ATCType.CAPSULE) {
            this.status = ChartStatus.COMPLETE;
        } else if (prescription.getAtcCode().getType() == ATCType.LIQUID) {
            this.status = ChartStatus.INJECTION;
        }
    }
}
