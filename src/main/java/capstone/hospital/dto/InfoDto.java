package capstone.hospital.dto;

import capstone.hospital.dto.type.MemberType;
import lombok.Data;

@Data
public class InfoDto {

    private String phoneNumber;
    private String city;
    private String street;
    private String zipcode;
    private MemberType memberType;

    public InfoDto(String phoneNumber, String city, String street, String zipcode) {
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public InfoDto(String phoneNumber, String city, String street, String zipcode, MemberType memberType) {
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.memberType = memberType;
    }
}
