package capstone.hospital.repository.impl;

import capstone.hospital.domain.Inpatient;
import capstone.hospital.repository.custom.InpatientRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static capstone.hospital.domain.QDoctor.doctor;
import static capstone.hospital.domain.QInpatient.*;
import static capstone.hospital.domain.QPatient.*;
import static org.springframework.util.StringUtils.hasText;

public class InpatientRepositoryImpl implements InpatientRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public InpatientRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Inpatient> findByPatientName(String name) {
        return queryFactory
                .selectFrom(inpatient)
                .join(inpatient.patient, patient)
                .where(patient.info.name.eq(name))
                .stream().findAny();
    }

    @Override
    public List<Inpatient> findWaitedByName(String name) {
        return queryFactory
                .selectFrom(inpatient)
                .where(
                        nameEq(name),
                        inpatient.ward.isNull()
                )
                .fetch();
    }

    private Predicate nameEq(String name) {
        return hasText(name) ? inpatient.patient.info.name.eq(name) : null;
    }
}
