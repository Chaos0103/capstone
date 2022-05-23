package capstone.hospital.repository;

import capstone.hospital.domain.Patient;
import capstone.hospital.repository.custom.PatientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>, PatientRepositoryCustom, QuerydslPredicateExecutor<Patient> {

    Optional<Patient> findByLoginId(String loginId);

    Optional<Patient> findByInfoRrn(String rrn);

    Optional<Patient> findByInfoName(String name);
}
