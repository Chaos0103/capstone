package capstone.hospital.repository.impl;

import capstone.hospital.domain.Report;
import capstone.hospital.repository.custom.ReportRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QPatient.patient;
import static capstone.hospital.domain.QReport.report;

public class ReportRepositoryImpl implements ReportRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReportRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Report> findReport(Long id) {
        return queryFactory
                .selectFrom(report)
                .join(report.patient, patient)
                .where(patient.id.eq(id))
                .fetch();
    }
}
