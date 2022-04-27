package capstone.hospital.repository;

import capstone.hospital.domain.KCDCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KCDCodeRepository extends JpaRepository<KCDCode, String> {
}
