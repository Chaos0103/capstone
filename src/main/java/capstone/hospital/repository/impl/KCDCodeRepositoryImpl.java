package capstone.hospital.repository.impl;

import capstone.hospital.domain.KCDCode;
import capstone.hospital.repository.custom.KCDCodeRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QKCDCode.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class KCDCodeRepositoryImpl implements KCDCodeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public KCDCodeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
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
