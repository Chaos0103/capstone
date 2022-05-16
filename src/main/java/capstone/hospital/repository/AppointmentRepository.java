package capstone.hospital.repository;

import capstone.hospital.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    Optional<Appointment> findByPatientIdAndMedicalDateAndMedicalTime(Long patientId, String medicalDate, String medicalTime);
}
