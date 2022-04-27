package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.ATCType;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ATCCode extends BaseEntity {

    @Id
    @Column(name = "atccode_id")
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private ATCType type;
    private int stock;
}
