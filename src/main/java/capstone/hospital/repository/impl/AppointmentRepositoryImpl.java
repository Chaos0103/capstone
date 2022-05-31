package capstone.hospital.repository.impl;

import capstone.hospital.domain.Appointment;
import capstone.hospital.domain.QAppointment;
import capstone.hospital.domain.enumtype.AppointmentStatus;
import capstone.hospital.repository.custom.AppointmentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static capstone.hospital.domain.QAppointment.*;

public class AppointmentRepositoryImpl implements AppointmentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AppointmentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Appointment> findAppointmentList(Long doctorId) {
        return queryFactory
                .selectFrom(appointment)
                .where(
                        appointment.doctor.id.eq(doctorId),
                        appointment.status.eq(AppointmentStatus.APPOINTMENT)
                )
                .fetch();
    }
}
