package capstone.hospital.service;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.enumtype.SexType;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.dto.UploadFile;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.PatientQueryRepository;
import capstone.hospital.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JoinServiceTest {

    @Autowired JoinService joinService;
    @Autowired PatientRepository patientRepository;
    @Autowired DoctorRepository doctorRepository;
    @Autowired PatientQueryRepository patientQueryRepository;
    @Autowired EntityManager em;

    @BeforeEach
    public void before() {
        Patient patient = createMember("123456", "1000000", "loginId");
        joinService.joinPatient(patient);
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("맴버 회원가입")
    public void joinMember() throws Exception {
        // given
        Patient patient = createMember("210415", "1000000", "memberId");

        // when
        Long memberId = joinService.joinPatient(patient);

        em.flush();
        em.clear();

        // then
        Patient findPatient = patientRepository.findById(memberId).get();

        assertThat(findPatient.getId()).isEqualTo(patient.getId());
        assertThat(findPatient.getInfo().getSex()).isEqualTo(SexType.MALE);
    }

    @Test
    @DisplayName("중복 회원 검증")
    public void duplicatedJoin() throws Exception {
        // given
        Patient newPatient = createMember("123456", "1000000", "newLoginId");

        // when
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> joinService.joinPatient(newPatient));

        // then
        assertThat(exception.getMessage()).isEqualTo("이미 가입된 회원입니다.");
    }

    @Test
    @DisplayName("중복 아이디 검증")
    public void duplicatedLoginId() throws Exception {
        // given
        Patient newPatient = createMember("222222", "2222222", "loginId");

        // when
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> joinService.joinPatient(newPatient));

        // then
        assertThat(exception.getMessage()).isEqualTo("이미 사용중인 아이디입니다.");
    }

    @Test
    @DisplayName("의사 회원가입")
    public void joinDoctor() throws Exception {
        // given
        Doctor doctor = createDoctor("123456", "1111111", "doctorId");

        // when
        Long doctorId = joinService.joinDoctor(doctor);

        em.flush();
        em.clear();

        // then
        Optional<Doctor> findDoctor = doctorRepository.findById(doctorId);

        assertThat(findDoctor).isNotEmpty();
        assertThat(findDoctor.get().getId()).isEqualTo(doctorId);
    }

    //== 편의 메서드 ==//
    private Patient createMember(String rrnFront, String rrnBack, String loginId) {
        Address address = new Address("city", "street", "zipcode");
        Information info = new Information("member", rrnFront, rrnBack, "010-1111-1111", address);
        return new Patient(loginId, "loginPw", info);
    }

    private Doctor createDoctor(String rrnFront, String rrnBack, String loginId) {
        Address address = new Address("city", "street", "zipcode");
        Information info = new Information("member", rrnFront, rrnBack, "010-1111-1111", address);
        return new Doctor(loginId, "loginPw", info, "123456", Major.OBGY, DoctorRank.PROFESSOR, new UploadFile(null, null));
    }
}