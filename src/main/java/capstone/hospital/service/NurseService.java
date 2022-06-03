package capstone.hospital.service;

import capstone.hospital.domain.Inpatient;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.Report;
import capstone.hospital.dto.*;
import capstone.hospital.exception.SearchException;
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
public class NurseService {

    private final PatientRepository patientRepository;
    private final InpatientRepository inpatientRepository;
    private final ReportRepository reportRepository;

    public PatientInfoDto findPatientInfo(String name) {
        Optional<Patient> patient = patientRepository.findByInfoName(name);
        return patient.map(PatientInfoDto::new).orElseGet(PatientInfoDto::new);
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

    public InpatientInfoDto findInpatientInfo(String name) {
        if (name == null) {
            return new InpatientInfoDto();
        } else {
            try {
                Optional<Inpatient> inpatient = inpatientRepository.findByPatientName(name);
                return new InpatientInfoDto(inpatient.get());
            } catch (Exception e) {
                throw new SearchException("error");
            }
        }
    }

    public List<CreateInpatientDto> findWaitInpatient(String name) {
        List<CreateInpatientDto> data = new ArrayList<>();
        List<Inpatient> waited = inpatientRepository.findWaitedByName(name);
        for (Inpatient inpatient : waited) {
            data.add(new CreateInpatientDto(inpatient));
        }
        return data;
    }

    public CreateInpatientDto findOneInpatient(Long inpatientId) {
        Optional<Inpatient> inpatient = inpatientRepository.findById(inpatientId);
        return new CreateInpatientDto(inpatient.get());
    }

    @Transactional
    public void createInpatient(Long inpatientId, CreateInpatientInfoDto data) {
        Inpatient findInpatient = inpatientRepository.findById(inpatientId).get();
        findInpatient.approve(data);
    }
}
