package capstone.hospital.service;

import capstone.hospital.domain.Appointment;
import capstone.hospital.domain.Medical;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.enumtype.AppointmentStatus;
import capstone.hospital.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalRepository medicalRepository;

    public Long createAppointment(Long memberId, Long medicalId) {
        Optional<Patient> findMember = patientRepository.findById(memberId);
        Optional<Medical> findMedical = medicalRepository.findById(medicalId);
        if (findMember.isEmpty() || findMedical.isEmpty()) {
            throw new IllegalStateException("예약에 실패했습니다.");
        } else {
            Appointment appointment = new Appointment(findMember.get(), findMedical.get());
            findMedical.get().success();
            appointmentRepository.save(appointment);
            return appointment.getId();
        }
    }

    public void cancelAppointment(Long memberId, Long appointmentId) {
        Optional<Patient> findMember = patientRepository.findById(memberId);
        Optional<Appointment> findAppointment = appointmentRepository.findById(appointmentId);
        if (findAppointment.isEmpty()) {
            throw new IllegalStateException("예약된 진료가 없습니다.");
        } else if (findAppointment.get().getStatus().equals(AppointmentStatus.CANCEL)) {
            throw new IllegalStateException("이미 취소된 예약입니다.");
        } else {
            findAppointment.get().cancel();
            findAppointment.get().getMedical().cancel();
        }
    }

    /**
     * update
     */
    public void updateAppointment(Long memberId) {
        Optional<Patient> findMember = patientRepository.findById(memberId);


    }
}
