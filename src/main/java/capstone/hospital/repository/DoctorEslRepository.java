package capstone.hospital.repository;

import capstone.hospital.domain.DoctorEsl;
import capstone.hospital.repository.custom.DoctorEslRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DoctorEslRepository extends JpaRepository<DoctorEsl, Long>, DoctorEslRepositoryCustom, QuerydslPredicateExecutor<DoctorEsl> {
}
