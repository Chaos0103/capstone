package capstone.hospital.service;

import capstone.hospital.domain.*;
import capstone.hospital.dto.*;
import capstone.hospital.exception.SearchException;
import capstone.hospital.repository.*;
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
    private final ChartRepository chartRepository;
    private final NurseRepository nurseRepository;
    private final PrescriptionRepository prescriptionRepository;

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

    public List<PrescriptionDto> findPrescription(Long inpatientId) {
        List<PrescriptionDto> data = new ArrayList<>();
        if (inpatientId == null) {
            return data;
        }
        Optional<Inpatient> inpatient = inpatientRepository.findById(inpatientId);
        List<Prescription> prescriptions = inpatient.get().getReport().getPrescriptions();
        for (Prescription prescription : prescriptions) {
            data.add(new PrescriptionDto(prescription));
        }
        return data;
    }

    @Transactional
    public void createChart(Long prescriptionId, Long nurseId) {
        Nurse findNurse = nurseRepository.findById(nurseId).get();
        Prescription prescription = prescriptionRepository.findById(prescriptionId).get();
        chartRepository.save(new Chart(prescription, findNurse.getInfo().getName()));
    }

    public List<Chart> findAllChart(Long inpatientId) {
        List<Chart> data = new ArrayList<>();
        if (inpatientId == null) {
            return data;
        }
        Inpatient inpatient = inpatientRepository.findById(inpatientId).get();
        List<Prescription> prescriptions = inpatient.getReport().getPrescriptions();
        for (Prescription prescription : prescriptions) {
            Optional<Chart> find = chartRepository.findByPrescriptionId(prescription.getId());
            find.ifPresent(data::add);
        }
        return data;
    }

    public List<Chart> findChart() {
        List<Chart> charts = chartRepository.findByStatus();
        if (charts.isEmpty()) {
            return new ArrayList<>();
        }
        return charts;
    }

    @Transactional
    public void outInpatient(Long inpatientId) {
        Inpatient inpatient = inpatientRepository.findById(inpatientId).get();
        inpatient.out();
    }

}
