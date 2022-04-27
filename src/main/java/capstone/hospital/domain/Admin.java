package capstone.hospital.domain;

import capstone.hospital.domain.valuetype.Information;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Admin {

    @Id @GeneratedValue
    @Column(name = "admin_id")
    private Long id;
    private String loginId;
    private String loginPw;

    @Embedded
    private Information info;
}
