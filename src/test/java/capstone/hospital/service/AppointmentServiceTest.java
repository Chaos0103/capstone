package capstone.hospital.service;

import capstone.hospital.domain.Appointment;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.enumtype.AppointmentStatus;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
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
class AppointmentServiceTest {

    @Autowired
    AppointmentService appointmentService;
//    @Autowired AppointmentRepository appointmentRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("진료 예약")
    public void createAppointment() throws Exception {
        // given
        Patient patient = createMember();

        // when
        Long appointment = appointmentService.createAppointment(patient.getId(), null, "2022/01/01", "12:00");

        // then
//        assertThat();
    }

    @Test
    @DisplayName("진료 취소")
    public void cancelAppointment() throws Exception {
        // given
        Patient patient = createMember();
        Medical medical = new Medical(null, "220413", "1500");

        Long appointmentId = appointmentService.createAppointment(patient.getId(), medical.getId());
        // when
        appointmentService.cancelAppointment(patient.getId(), appointmentId);

        em.flush();
        em.clear();

        // then
        Optional<Appointment> findAppointment = appointmentRepository.findById(appointmentId);

        assertThat(findAppointment.get().getStatus()).isEqualTo(AppointmentStatus.CANCEL);
        assertThat(findAppointment.get().getMedical().getStatus()).isEqualTo(MedicalStatus.POSSIBLE);
    }

    @Test
    @DisplayName("진료 취소 실패")
    public void cancelFailAppointment() throws Exception {
        // given
        Patient patient = createMember();
        Medical medical = new Medical(null, "220413", "1500");
        em.persist(patient);
        em.persist(medical);
        em.flush();
        em.clear();

        Long appointmentId = appointmentService.createAppointment(patient.getId(), medical.getId());

        appointmentService.cancelAppointment(patient.getId(), appointmentId);
        em.flush();
        em.clear();
        // when

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> appointmentService.cancelAppointment(patient.getId(), appointmentId));

        // then
        assertThat(exception.getMessage()).isEqualTo("이미 취소된 예약입니다.");
    }

    @Test
    @DisplayName("진료 수정")
    public void updateAppointment() throws Exception {
        // given
        Patient patient = createMember();
        Medical medical = new Medical(null, "220413", "1500");
        em.persist(patient);
        em.persist(medical);
        em.flush();
        em.clear();

        // when


        // then
    }

    //== 편의 메서드 ==//
    private Patient createMember() {
        Address address = new Address("city", "street", "zipcode");
        Information info = new Information("member", "111111", "1111111", "010-1111-1111", address);
        return new Patient("loginId", "loginPw", info);
    }
}