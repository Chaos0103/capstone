package capstone.hospital.repository;

import capstone.hospital.domain.ATCCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATCCodeRepository extends JpaRepository<ATCCode, String> {

}
