package capstone.hospital;

import capstone.hospital.domain.Patient;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HospitalApplicationTests {

	@Test
	void contextLoads() {
		Patient patient = createMember();

	}

	//== 편의 메서드 ==//
	private Patient createMember() {
		Address address = new Address("city", "street", "zipcode");
		Information info = new Information("member", "111111", "1111111", "010-1111-1111", address);
		return new Patient("loginId", "loginPw", info);
	}
}
