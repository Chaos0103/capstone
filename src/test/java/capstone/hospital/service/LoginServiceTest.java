package capstone.hospital.service;

import capstone.hospital.domain.Patient;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired private LoginService loginService;
    @Autowired private JoinService joinService;
    @Autowired private PatientRepository patientRepository;
    @Autowired private EntityManager em;

    @BeforeEach
    public void before() {
        Patient patient = createMember("123456", "1000000", "loginId");
//        joinService.joinMember(patient);
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("맴버 로그인")
    public void loginMember() throws Exception {
        // given

        // when
//        Long memberId = loginService.login("loginId", "loginPw");

        // then
//        Patient loginPatient = patientRepository.findById(memberId).get();
//
//        assertThat(loginPatient.getId()).isEqualTo(memberId);
    }

    @Test
    @DisplayName("맴버 로그인 실패")
    public void loginFailMember() throws Exception {
        // given

        // when
        IllegalStateException failLoginId = assertThrows(IllegalStateException.class, () -> loginService.login("none", "loginPw"));
        IllegalStateException failLoginPw = assertThrows(IllegalStateException.class, () -> loginService.login("loginId", "none"));

        // then
        assertThat(failLoginId.getMessage()).isEqualTo("존재하지 않는 아이디입니다.");
        assertThat(failLoginPw.getMessage()).isEqualTo("비밀번호가 틀렸습니다.");
    }

    @Test
    @DisplayName("맴버 비밀번호 변경")
    public void changePw() throws Exception {
        // given
        Patient memberId = loginService.login("loginId", "loginPw");
//        Patient findPatient = patientRepository.findById(memberId).get();

        // when
//        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> loginService.changePw(findPatient.getId(), "failPw", "newPw"));
//        loginService.changePw(findPatient.getId(), "loginPw", "newPw");

        // then

        em.flush();
//        assertThat(findPatient.getLoginPw()).isEqualTo("newPw");
//        assertThat(exception.getMessage()).isEqualTo("현재 비밀번호가 다릅니다.");
    }

    //== 편의 메서드 ==//
    private Patient createMember(String rrnFront, String rrnBack, String loginId) {
        Address address = new Address("city", "street", "zipcode");
        Information info = new Information("member", rrnFront, rrnBack, "010-1111-1111", address);
        return new Patient(loginId, "loginPw", info);
    }
}