package capstone.hospital.repository;

import capstone.hospital.domain.QDoctor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QDoctor.*;

@Repository
public class DoctorQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public DoctorQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<String> findLoginId(String name, String phoneNumber) {
        return queryFactory
                .select(doctor.loginId)
                .from(doctor)
                .where(doctor.info.name.eq(name), doctor.info.phoneNumber.eq(phoneNumber))
                .fetch();
    }

    public List<String> findLoginPw(String name, String phoneNumber, String loginId) {
        return queryFactory
                .select(doctor.loginPw)
                .from(doctor)
                .where(doctor.info.name.eq(name), doctor.info.phoneNumber.eq(phoneNumber), doctor.loginId.eq(loginId))
                .fetch();
    }
}