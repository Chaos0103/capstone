package capstone.hospital.repository.impl;

import capstone.hospital.domain.Chart;
import capstone.hospital.domain.QChart;
import capstone.hospital.domain.enumtype.ChartStatus;
import capstone.hospital.repository.custom.ChartRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QChart.*;

public class ChartRepositoryImpl implements ChartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ChartRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Chart> findByStatus() {
        return queryFactory
                .selectFrom(chart)
                .where(
                        chart.status.eq(ChartStatus.INJECTION)
                )
                .fetch();
    }
}
