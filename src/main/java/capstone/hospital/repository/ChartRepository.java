package capstone.hospital.repository;

import capstone.hospital.domain.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartRepository extends JpaRepository<Chart, Long> {
}
