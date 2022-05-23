package capstone.hospital.repository.impl;

import capstone.hospital.domain.DoctorEsl;
import capstone.hospital.repository.custom.DoctorEslRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QDoctorEsl.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class DoctorEslRepositoryImpl implements DoctorEslRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DoctorEslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<DoctorEsl> findByName(String name) {
        return queryFactory
                .selectFrom(doctorEsl)
                .where(
                        nameEq(name)
                )
                .fetch();
    }

    private Predicate nameEq(String name) {
        return hasText(name) ? doctorEsl.doctor.info.name.eq(name) : null;
    }
}
