package capstone.hospital.repository;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.enumtype.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByLoginId(String loginId);

    Optional<Doctor> findByInfoRrn(String rrn);

    List<Doctor> findByMajor(Major major);

    List<Object> findByInfoNameAndInfoPhoneNumber(String name, String phoneNumber);
}
