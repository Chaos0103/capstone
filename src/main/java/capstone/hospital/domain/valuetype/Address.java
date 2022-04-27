package capstone.hospital.domain.valuetype;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {}

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public void changeAddress(Address updateAddress) {
        this.city = updateAddress.getCity();
        this.street = updateAddress.getStreet();
        this.zipcode = updateAddress.getZipcode();
    }
}
