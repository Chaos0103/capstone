package capstone.hospital.repository.custom;

import capstone.hospital.domain.Report;

import java.util.List;

public interface ReportRepositoryCustom {

    List<Report> findReport(Long id);
}
