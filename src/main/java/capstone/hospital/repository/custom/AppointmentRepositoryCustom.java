package capstone.hospital.repository.custom;

import capstone.hospital.domain.Appointment;

import java.util.List;

public interface AppointmentRepositoryCustom {
    List<Appointment> findAppointmentList(Long doctorId);
}
