package capstone.hospital.repository;

import capstone.hospital.controller.patient.form.SearchForm;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.enumtype.Major;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.print.Doc;
import java.util.List;

import static capstone.hospital.domain.QDoctor.*;
import static org.springframework.util.StringUtils.hasText;

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

    public List<Doctor> searchByDoctor(SearchForm form) {
        return queryFactory
                .selectFrom(doctor)
                .where(
                        majorEq(form.getMajor()),
                        nameEq(form.getDoctorName())
                )
                .fetch();
    }

    /**
     * 승인되지 않은 의사 조회
     */
    public List<Doctor> notApproveDoctor(String name) {
        return queryFactory
                .selectFrom(doctor)
                .where(
                        nameEq(name),
                        doctor.approvalAdmin.isNull()
                )
                .fetch();
    }

    /**
     * 승인된 의사 조회
     */
    public List<Doctor> approveDoctor(String name) {
        return queryFactory
                .selectFrom(doctor)
                .where(
                        nameEq(name),
                        doctor.approvalAdmin.isNotNull()
                )
                .fetch();
    }

    private BooleanExpression majorEq(Major major) {
        return major != null ? doctor.major.eq(major) : null;
    }

    private Predicate nameEq(String name) {
        return hasText(name) ? doctor.info.name.eq(name) : null;
    }

}