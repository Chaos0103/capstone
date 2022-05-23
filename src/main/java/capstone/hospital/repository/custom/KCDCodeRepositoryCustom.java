package capstone.hospital.repository.custom;

import capstone.hospital.domain.KCDCode;

import java.util.List;

public interface KCDCodeRepositoryCustom {
    List<KCDCode> findByName(String name);
}
