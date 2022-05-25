package capstone.hospital.domain.valuetype;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String zipcode;
    private String mainAddress;
    private String subAddress;

    protected Address() {}

    public Address(String zipcode, String mainAddress, String subAddress) {
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
    }

    public void changeAddress(Address updateAddress) {
        this.zipcode = updateAddress.getZipcode();
        this.mainAddress = updateAddress.getMainAddress();
        this.subAddress = updateAddress.getSubAddress();
    }
}
