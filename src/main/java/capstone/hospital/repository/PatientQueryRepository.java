package capstone.hospital.repository;

import capstone.hospital.domain.QPatient;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QPatient.*;


@Repository
public class PatientQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PatientQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<String> findByRrn(String rrn) {

        return queryFactory
                .select(patient.info.rrn)
                .from(patient)
                .where(patient.info.rrn.eq(rrn))
                .fetch();
    }

}
