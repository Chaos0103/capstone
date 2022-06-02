package capstone.hospital.repository;

import capstone.hospital.domain.Inpatient;
import capstone.hospital.repository.custom.InpatientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface InpatientRepository extends JpaRepository<Inpatient, Long>, InpatientRepositoryCustom, QuerydslPredicateExecutor<Inpatient> {
    List<Inpatient> findDoctorByDoctorIdAndWardNotNull(Long doctorId);
}
