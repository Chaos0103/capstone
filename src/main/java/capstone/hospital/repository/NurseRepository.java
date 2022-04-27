package capstone.hospital.repository;

import capstone.hospital.domain.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Optional<Nurse> findByLoginId(String loginId);
}
