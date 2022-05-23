package capstone.hospital.repository;

import capstone.hospital.domain.KCDCode;
import capstone.hospital.repository.custom.KCDCodeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface KCDCodeRepository extends JpaRepository<KCDCode, String>, KCDCodeRepositoryCustom, QuerydslPredicateExecutor<KCDCode> {
}
