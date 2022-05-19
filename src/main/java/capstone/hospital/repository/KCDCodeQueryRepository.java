package capstone.hospital.repository;

import capstone.hospital.domain.KCDCode;
import capstone.hospital.domain.QKCDCode;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QATCCode.aTCCode;
import static capstone.hospital.domain.QKCDCode.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class KCDCodeQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public KCDCodeQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<KCDCode> findByName(String name) {
        return queryFactory
                .selectFrom(kCDCode)
                .where(nameEq(name))
                .fetch();
    }

    private Predicate nameEq(String name) {
        return hasText(name) ? kCDCode.name.eq(name) : null;
    }
}
