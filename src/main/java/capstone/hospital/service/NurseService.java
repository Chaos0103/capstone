package capstone.hospital.service;

import capstone.hospital.domain.Patient;
import capstone.hospital.domain.Report;
import capstone.hospital.dto.PatientInfoDto;
import capstone.hospital.dto.ReportDto;
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
public class NurseService {

    private final PatientRepository patientRepository;
    private final ReportRepository reportRepository;

    public PatientInfoDto findPatientInfo(String name) {
        Optional<Patient> patient = patientRepository.findByInfoName(name);
        if (patient.isPresent()) {
            return new PatientInfoDto(patient.get());
        } else {
            return new PatientInfoDto();
        }
    }

    public List<ReportDto> findReport(Long patientId) {
        List<ReportDto> data = new ArrayList<>();
        if (patientId != null) {
            List<Report> reports = reportRepository.findReport(patientId);
            for (Report report : reports) {
                data.add(new ReportDto(report));
            }
        }
        return data;
    }
}
