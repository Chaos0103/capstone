package capstone.hospital.repository;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.repository.custom.DoctorRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long>, DoctorRepositoryCustom, QuerydslPredicateExecutor<Doctor> {

    Optional<Doctor> findByLoginId(String loginId);

    Optional<Doctor> findByInfoRrn(String rrn);

    List<Doctor> findByMajor(Major major);

    Optional<Doctor> findByInfoName(String name);

    List<Object> findByInfoNameAndInfoPhoneNumber(String name, String phoneNumber);
}
