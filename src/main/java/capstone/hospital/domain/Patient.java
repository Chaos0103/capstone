package capstone.hospital.domain;

import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.dto.InformationDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Patient {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;
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
            loginPw = newPw;
        } else {
            throw new IllegalStateException("현재 비밀번호가 다릅니다.");
        }
    }

    public void changeInfo(InformationDto informationDto) {
        Address address = new Address(informationDto.getCity(), informationDto.getStreet(), informationDto.getZipcode());
        info.changeInformation(informationDto.getPhoneNumber(), address);
    }
}
