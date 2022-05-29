package capstone.hospital.repository.custom;

import capstone.hospital.domain.Inpatient;

import java.util.List;
import java.util.Optional;

public interface InpatientRepositoryCustom {
    Optional<Inpatient> findByPatientName(String name);

    List<Inpatient> findWaitedByName(String name);
}
