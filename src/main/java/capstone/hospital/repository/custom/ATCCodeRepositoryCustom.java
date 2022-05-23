package capstone.hospital.repository.custom;

import capstone.hospital.domain.ATCCode;

import java.util.List;

public interface ATCCodeRepositoryCustom {
    List<ATCCode> findByName(String name);
}
