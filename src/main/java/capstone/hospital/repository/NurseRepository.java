package capstone.hospital.repository;

import capstone.hospital.domain.Nurse;
import capstone.hospital.repository.custom.NurseRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse, Long>, NurseRepositoryCustom, QuerydslPredicateExecutor<Nurse> {

    Optional<Nurse> findByLoginId(String loginId);

    Optional<Nurse> findByInfoRrn(String rrn);
}
