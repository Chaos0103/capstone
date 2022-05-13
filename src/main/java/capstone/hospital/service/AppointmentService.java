package capstone.hospital.service;

import capstone.hospital.domain.Appointment;
import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Patient;
import capstone.hospital.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    /**
     * create
     */
    public Long createAppointment(Long patientId, Long doctorId, String medicalDate, String medicalDay) {
        Optional<Patient> findPatient = patientRepository.findById(patientId);
        Optional<Doctor> findDoctor = doctorRepository.findById(doctorId);

        if (findPatient.isEmpty() || findDoctor.isEmpty()) {
            throw new IllegalStateException("예약에 실패했습니다.");
        } else {
            return appointmentRepository.save(new Appointment(findPatient.get(), findDoctor.get(), medicalDate, medicalDay)).getId();
        }
    }

    /**
     * read
     */
    @Transactional(readOnly = true)
    public List<Appointment> readAppointment(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    /**
     * update
     */
    public void updateAppointment(Long appointmentId, Long doctorId, String medicalDate, String medicalDay) {
        Optional<Appointment> findInfo = appointmentRepository.findById(appointmentId);
        Optional<Doctor> findDoctor = doctorRepository.findById(doctorId);
        findInfo.get().update(findDoctor.get(), medicalDate, medicalDay);
    }

    /**
     * delete
     */
    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
