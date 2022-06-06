package capstone.hospital.domain.valuetype;

import capstone.hospital.domain.enumtype.WardType;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class Ward {

    @Enumerated(EnumType.STRING)
    private WardType wardType;

    private String roomNumber;
    private String bedNumber;

    public Ward() {

    }

    public Ward(WardType wardType, String roomNumber, String bedNumber) {
        this.wardType = wardType;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
    }
}
