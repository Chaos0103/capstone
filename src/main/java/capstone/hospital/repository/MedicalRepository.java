package capstone.hospital.repository;

import capstone.hospital.domain.Medical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRepository extends JpaRepository<Medical, Long> {
}
