package capstone.hospital.repository;

import capstone.hospital.domain.ATCCode;
import capstone.hospital.repository.custom.ATCCodeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ATCCodeRepository extends JpaRepository<ATCCode, String>, ATCCodeRepositoryCustom, QuerydslPredicateExecutor<ATCCode> {

}
