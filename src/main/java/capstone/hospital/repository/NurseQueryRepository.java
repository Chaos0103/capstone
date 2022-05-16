package capstone.hospital.repository;

import capstone.hospital.domain.Nurse;
import capstone.hospital.domain.QNurse;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QDoctor.doctor;
import static capstone.hospital.domain.QNurse.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class NurseQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public NurseQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<String> findLoginId(String name, String phoneNumber) {
        return queryFactory
                .select(nurse.loginId)
                .from(nurse)
                .where(nurse.info.name.eq(name), nurse.info.phoneNumber.eq(phoneNumber))
                .fetch();
    }

    public List<String> findLoginPw(String name, String phoneNumber, String loginId) {
        return queryFactory
                .select(nurse.loginPw)
                .from(nurse)
                .where(nurse.info.name.eq(name), nurse.info.phoneNumber.eq(phoneNumber), nurse.loginId.eq(loginId))
                .fetch();
    }

    /**
     * 승인된 간호사 조회
     */
    public List<Nurse> approveNurse(String name) {
        return queryFactory
                .selectFrom(nurse)
                .where(
                        nameEq(name),
                        nurse.approvalAdmin.isNotNull()
                )
                .fetch();
    }

    /**
     *
     */
    public List<Nurse> notApproveNurse(String name) {
        return queryFactory
                .selectFrom(nurse)
                .where(
                        nameEq(name),
                        nurse.approvalAdmin.isNull()
                )
                .fetch();
    }

    private Predicate nameEq(String name) {
        return hasText(name) ? nurse.info.name.eq(name) : null;
    }
}
