package capstone.hospital.service;

import capstone.hospital.domain.Patient;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.dto.InfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class BasicServiceTest {

    @Autowired
    BasicService basicService;
    @Autowired
    JoinService joinService;
    @Autowired EntityManager em;

    @Test
    @DisplayName("회원 정보 수정")
    @Commit
    public void changeInfo() throws Exception {
        // given
        Patient patient = createMember("220412", "1111111", "loginId");
        Long memberId = joinService.joinPatient(patient);
        InfoDto infoDto = new InfoDto("010-2222-2222", "newCity", "newStreet", "newZipcode");
        // when
        basicService.changeMemberInfo(memberId, infoDto);

        // then
        em.flush();

    }

    //== 편의 메서드 ==//
    private Patient createMember(String rrnFront, String rrnBack, String loginId) {
        Address address = new Address("city", "street", "zipcode");
        Information info = new Information("member", rrnFront, rrnBack, "010-1111-1111", address);
        return new Patient(loginId, "loginPw", info);
    }
}