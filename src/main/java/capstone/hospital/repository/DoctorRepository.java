package capstone.hospital.repository;

import capstone.hospital.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByLoginId(String loginId);

    Optional<Doctor> findByInfoRrn(String rrn);
}
