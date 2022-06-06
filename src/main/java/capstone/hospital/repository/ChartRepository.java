package capstone.hospital.repository;

import capstone.hospital.domain.Chart;
import capstone.hospital.repository.custom.ChartRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ChartRepository extends JpaRepository<Chart, Long>, ChartRepositoryCustom, QuerydslPredicateExecutor<Chart> {
    Optional<Chart> findByPrescriptionId(Long id);
}
