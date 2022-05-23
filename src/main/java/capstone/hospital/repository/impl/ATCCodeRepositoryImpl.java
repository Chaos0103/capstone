package capstone.hospital.repository.impl;

import capstone.hospital.domain.ATCCode;
import capstone.hospital.repository.custom.ATCCodeRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QATCCode.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class ATCCodeRepositoryImpl implements ATCCodeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ATCCodeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
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
