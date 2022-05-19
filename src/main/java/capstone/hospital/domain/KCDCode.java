package capstone.hospital.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class KCDCode extends BaseEntity {

    @Id
    @Column(name = "kcdcode_id")
    private String code;
    private String name;

    public KCDCode() {
    }

    public KCDCode(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
