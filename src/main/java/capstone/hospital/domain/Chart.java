package capstone.hospital.domain;

import lombok.Getter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Chart {

    @Id @GeneratedValue
    @Column(name = "chart_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    private Prescription prescription;

    private LocalDateTime createdDate;
    private LocalDateTime endDate;
    private String createdBy;
    private String endBy;
}
