package capstone.hospital.repository.impl;

import capstone.hospital.repository.custom.PatientRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QPatient.*;


@Repository
public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PatientRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<String> findLoginId(String name, String phoneNumber) {
        return queryFactory
                .select(patient.loginId)
                .from(patient)
                .where(
                        patient.info.name.eq(name),
                        patient.info.phoneNumber.eq(phoneNumber)
                )
                .fetch();
    }

    @Override
    public List<String> findLoginPw(String name, String phoneNumber, String loginId) {
        return queryFactory
                .select(patient.loginPw)
                .from(patient)
                .where(
                        patient.info.name.eq(name),
                        patient.info.phoneNumber.eq(phoneNumber),
                        patient.loginId.eq(loginId)
                )
                .fetch();
    }



}
