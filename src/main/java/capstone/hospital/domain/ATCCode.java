package capstone.hospital.domain;

import capstone.hospital.domain.enumtype.ATCType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ATCCode extends TimeBaseEntity {

    @Id
    @Column(name = "atccode_id")
    private String code;
    private String name;
    private String company;

    @Enumerated(EnumType.STRING)
    private ATCType type;

    public ATCCode(String code, String name, String company, ATCType type) {
        this.code = code;
        this.name = name;
        this.company = company;
        this.type = type;
    }
}
