package capstone.hospital.domain.valuetype;

import capstone.hospital.domain.enumtype.WardType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Ward {

    @Enumerated(EnumType.STRING)
    private WardType wardType;

    private int roomNumber;
    private int bedNumber;

}
