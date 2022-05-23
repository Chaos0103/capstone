package capstone.hospital.service;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.KCDCode;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.Report;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.KCDCodeRepository;
import capstone.hospital.repository.PatientRepository;
import capstone.hospital.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final KCDCodeRepository kcdCodeRepository;
    private final ReportRepository reportRepository;

    /**
     * 진단서 등록
     */
    @Transactional
    public void report(Long patientId, Long doctorId, String kdcCode) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        Optional<KCDCode> code = kcdCodeRepository.findById(kdcCode);
        reportRepository.save(new Report(patient.get(), doctor.get(), code.get()));
    }
}
