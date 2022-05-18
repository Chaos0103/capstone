package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.ATCType;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ATCCode extends BaseEntity {

    @Id
    @Column(name = "atccode_id")
    private String code;
    private String name;
    private String company;

    @Enumerated(EnumType.STRING)
    private ATCType type;
    private int stock;

    public ATCCode() {

    }

    public ATCCode(String code, String name, String company, ATCType type, int stock) {
        this.code = code;
        this.name = name;
        this.company = company;
        this.type = type;
        this.stock = stock;
    }
}
