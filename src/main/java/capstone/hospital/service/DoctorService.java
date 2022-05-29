package capstone.hospital.service;

import capstone.hospital.domain.*;
import capstone.hospital.dto.PatientInfoDto;
import capstone.hospital.dto.PrescriptionDto;
import capstone.hospital.dto.ReportDto;
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
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final KCDCodeRepository kcdCodeRepository;
    private final ReportRepository reportRepository;
    private final ATCCodeRepository atcCodeRepository;
    private final PrescriptionRepository prescriptionRepository;

    /**
     * 진단서 등록
     */
    @Transactional
    public void report(Long patientId, Long doctorId, String kdcCode, String content, List<PrescriptionDto> prescriptions) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        Optional<KCDCode> code = kcdCodeRepository.findById(kdcCode);
        Report save = reportRepository.save(new Report(patient.get(), doctor.get(), code.get(), content));
        for (PrescriptionDto prescription : prescriptions) {
            Optional<ATCCode> findCode = atcCodeRepository.findById(prescription.getMedicineCode());
            Prescription newPrescription = new Prescription(findCode.get(), save, prescription.getSingleDose(), prescription.getDailyDose(), prescription.getTotalDoseDays());
            newPrescription.addPrescription();
            prescriptionRepository.save(newPrescription);
        }
    }

    public PatientInfoDto findPatientInfo(String name) {
        Optional<Patient> patient = patientRepository.findByInfoName(name);
        return getPatientInfoDto(patient);
    }

    public PatientInfoDto findPatientInfoById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return getPatientInfoDto(patient);
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

    //== 편의 메서드 ==//
    private PatientInfoDto getPatientInfoDto(Optional<Patient> patient) {
        return patient.map(PatientInfoDto::new).orElseGet(PatientInfoDto::new);
    }
}
