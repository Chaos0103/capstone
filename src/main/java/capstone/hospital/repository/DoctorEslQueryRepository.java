package capstone.hospital.repository;

import capstone.hospital.domain.ATCCode;
import capstone.hospital.domain.DoctorEsl;
import capstone.hospital.domain.QDoctorEsl;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QATCCode.aTCCode;
import static capstone.hospital.domain.QDoctorEsl.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class DoctorEslQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public DoctorEslQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<DoctorEsl> findByName(String name) {
        return queryFactory
                .selectFrom(doctorEsl)
                .where(nameEq(name))
                .fetch();
    }

    private Predicate nameEq(String name) {
        return hasText(name) ? doctorEsl.doctor.info.name.eq(name) : null;
    }
}
