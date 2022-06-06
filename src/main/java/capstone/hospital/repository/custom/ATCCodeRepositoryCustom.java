package capstone.hospital.repository.custom;

import capstone.hospital.domain.ATCCode;
import capstone.hospital.domain.enumtype.ATCType;

import java.util.List;

public interface ATCCodeRepositoryCustom {
    List<ATCCode> findByName(String name);

    int countType(ATCType type);
}
