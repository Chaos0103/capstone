package capstone.hospital.domain.valuetype;

import capstone.hospital.domain.enumtype.SexType;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class Information {

    private String name;
    private String rrn;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private SexType sex;

    @Embedded
    private Address address;

    protected Information() {}
    public Information(String name, String rrnFront, String rrnBack, String phoneNumber, Address address) {
        this.name = name;
        this.rrn = RRNMake(rrnFront, rrnBack);
        this.sex = findSexType(rrnBack);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void changeInformation(String phoneNumber, Address address) {
        this.phoneNumber = phoneNumber;
        this.address.changeAddress(address);
    }

    //== 비즈니스 로직 ==//
    private String RRNMake(String rrnFront, String rrnBack) {
        return rrnFront + '-' + rrnBack;
    }

    private SexType findSexType(String rrnBack) {
        if (rrnBack.charAt(0) == '1' || rrnBack.charAt(0) == '3') {
            return SexType.MALE;
        } else if (rrnBack.charAt(0) == '2' || rrnBack.charAt(0) == '4') {
            return SexType.FEMALE;
        } else {
            throw new IllegalStateException("잘못된 주민등록번호 입니다.");
        }
    }
}
