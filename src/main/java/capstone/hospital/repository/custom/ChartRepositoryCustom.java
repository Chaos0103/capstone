package capstone.hospital.repository.custom;

import capstone.hospital.domain.Chart;

import java.util.List;

public interface ChartRepositoryCustom {
    List<Chart> findByStatus();
}
