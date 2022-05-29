package capstone.hospital.repository;

import capstone.hospital.domain.Inpatient;
import capstone.hospital.repository.custom.InpatientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InpatientRepository extends JpaRepository<Inpatient, Long>, InpatientRepositoryCustom, QuerydslPredicateExecutor<Inpatient> {

}
