package capstone.hospital.repository;

import capstone.hospital.domain.Report;
import capstone.hospital.repository.custom.ReportRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom, QuerydslPredicateExecutor<Report> {
}
