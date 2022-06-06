package capstone.hospital.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KCDCode extends TimeBaseEntity {

    @Id
    @Column(name = "kcdcode_id")
    private String code;
    private String name;

    public KCDCode(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
