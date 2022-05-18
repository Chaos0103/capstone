package capstone.hospital.repository;

import capstone.hospital.domain.ATCCode;
import capstone.hospital.domain.QATCCode;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QATCCode.*;
import static capstone.hospital.domain.QDoctor.doctor;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class ATCCodeQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ATCCodeQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ATCCode> findByName(String name) {
        return queryFactory
                .selectFrom(aTCCode)
                .where(nameEq(name))
                .fetch();
    }

    private Predicate nameEq(String name) {
        return hasText(name) ? aTCCode.name.eq(name) : null;
    }
}
