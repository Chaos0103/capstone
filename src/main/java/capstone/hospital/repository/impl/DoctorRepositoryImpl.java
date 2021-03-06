package capstone.hospital.repository.impl;

import capstone.hospital.controller.patient.form.SearchForm;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.repository.custom.DoctorRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QDoctor.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class DoctorRepositoryImpl implements DoctorRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DoctorRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<String> findLoginId(String name, String phoneNumber) {
        return queryFactory
                .select(doctor.loginId)
                .from(doctor)
                .where(doctor.info.name.eq(name), doctor.info.phoneNumber.eq(phoneNumber))
                .fetch();
    }

    @Override
    public List<String> findLoginPw(String name, String phoneNumber, String loginId) {
        return queryFactory
                .select(doctor.loginPw)
                .from(doctor)
                .where(doctor.info.name.eq(name), doctor.info.phoneNumber.eq(phoneNumber), doctor.loginId.eq(loginId))
                .fetch();
    }

    @Override
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
    @Override
    public List<Doctor> notApproveDoctor(String name, int startIndex, int pageSize) {
        return queryFactory
                .selectFrom(doctor)
                .where(
                        nameEq(name),
                        doctor.approvalAdmin.isNull()
                )
                .offset(startIndex)
                .limit(pageSize)
                .fetch();
    }

    /**
     * 승인되지 않은 의사 수
     */
    @Override
    public int notApproveDoctorCnt(String name) {
        return (int) queryFactory
                .selectFrom(doctor)
                .where(
                        nameEq(name),
                        doctor.approvalAdmin.isNull()
                )
                .stream().count();
    }

    /**
     * 승인된 의사 조회
     */
    @Override
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