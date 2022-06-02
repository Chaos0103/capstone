package capstone.hospital.service;

import capstone.hospital.domain.Doctor;
import capstone.hospital.domain.Inpatient;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.Report;
import capstone.hospital.dto.InpatientViewDto;
import capstone.hospital.repository.DoctorRepository;
import capstone.hospital.repository.InpatientRepository;
import capstone.hospital.repository.PatientRepository;
import capstone.hospital.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    public void createInpatient(Long patientId, Long doctorId, Long reportId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        Optional<Report> report = reportRepository.findById(reportId);
        inpatientRepository.save(new Inpatient(patient.get(), doctor.get(), report.get()));
    }

    public long totalInpatient() {
        return inpatientRepository.countInpatient();
    }

    public long todayInpatient() {
        return inpatientRepository.todayInpatient();
    }

    public long myInpatient(Long doctorId) {
        return inpatientRepository.myInpatient(doctorId);
    }

    public List<InpatientViewDto> myInpatientInfo(Long doctorId) {
        List<InpatientViewDto> data = new ArrayList<>();
        List<Inpatient> inpatients = inpatientRepository.findDoctorByDoctorIdAndWardNotNull(doctorId);
        for (Inpatient inpatient : inpatients) {
            data.add(new InpatientViewDto(inpatient));
        }
        return data;
    }
}
