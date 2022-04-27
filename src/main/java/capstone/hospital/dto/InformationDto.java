package capstone.hospital.dto;

import lombok.Data;

@Data
public class InformationDto {

    private String phoneNumber;
    private String city;
    private String street;
    private String zipcode;

    public InformationDto(String phoneNumber, String city, String street, String zipcode) {
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
