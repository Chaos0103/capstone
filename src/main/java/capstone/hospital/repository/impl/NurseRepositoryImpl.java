package capstone.hospital.repository.impl;

import capstone.hospital.domain.Nurse;
import capstone.hospital.repository.custom.NurseRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QNurse.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class NurseRepositoryImpl implements NurseRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NurseRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<String> findLoginId(String name, String phoneNumber) {
        return queryFactory
                .select(nurse.loginId)
                .from(nurse)
                .where(nurse.info.name.eq(name), nurse.info.phoneNumber.eq(phoneNumber))
                .fetch();
    }

    @Override
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
    @Override
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
    @Override
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
