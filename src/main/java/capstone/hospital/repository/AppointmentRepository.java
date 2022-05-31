package capstone.hospital.repository;

import capstone.hospital.domain.Appointment;
import capstone.hospital.repository.custom.AppointmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>, AppointmentRepositoryCustom, QuerydslPredicateExecutor<Appointment> {

    List<Appointment> findByPatientId(Long patientId);

    Optional<Appointment> findByPatientIdAndMedicalDateAndMedicalTime(Long patientId, String medicalDate, String medicalTime);
}
