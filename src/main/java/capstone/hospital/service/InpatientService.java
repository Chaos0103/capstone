package capstone.hospital.service;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Inpatient;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.Report;
import capstone.hospital.domain.enumtype.WardType;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.InpatientRepository;
import capstone.hospital.repository.PatientRepository;
import capstone.hospital.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InpatientService {

    private final InpatientRepository inpatientRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ReportRepository reportRepository;

    @Transactional
    public void createInpatient(Long patientId, Long doctorId, Long reportId, WardType type) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        Optional<Report> report = reportRepository.findById(reportId);
        inpatientRepository.save(new Inpatient(patient.get(), doctor.get(), report.get(), type));
    }
}
