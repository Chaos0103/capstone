package capstone.hospital.domain;

import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.dto.InfoDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Patient {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20, updatable = false)
    private String loginId;
    @Column(length = 20)
    private String loginPw;

    @Embedded
    private Information info;

    //== 생성 메서드 ==//
    public Patient() {
    }

    public Patient(String loginId, String loginPw, Information info) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.info = info;
    }

    //== 비즈니스 로직==//
    public void changePw(String oldPw, String newPw) {
        if (loginPw.equals(oldPw)) {
            this.loginPw = newPw;
        } else {
            throw new IllegalStateException("현재 비밀번호가 다릅니다.");
        }
    }

    public void changeInfo(InfoDto infoDto) {
        Address address = new Address(infoDto.getCity(), infoDto.getStreet(), infoDto.getZipcode());
        info.changeInformation(infoDto.getPhoneNumber(), address);
    }
}

