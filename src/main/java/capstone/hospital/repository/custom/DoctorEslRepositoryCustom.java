package capstone.hospital.repository.custom;

import capstone.hospital.domain.DoctorEsl;

import java.util.List;

public interface DoctorEslRepositoryCustom {
    List<DoctorEsl> findByName(String name);


}
