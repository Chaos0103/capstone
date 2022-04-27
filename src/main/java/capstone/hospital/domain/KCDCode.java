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
    private String id;
    private String name;

    public KCDCode() {
    }

    public KCDCode(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
