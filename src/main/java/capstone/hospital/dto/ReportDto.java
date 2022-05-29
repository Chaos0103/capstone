package capstone.hospital.dto;

import capstone.hospital.domain.Prescription;
import capstone.hospital.domain.Report;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReportDto {

    private Long id;
    private LocalDateTime date;
    private String diseaseName;
    private String doctorName;
    private String major;
    private List<PrescriptionDto> prescriptions = new ArrayList<>();

    public ReportDto(Report report) {
        this.id = report.getId();
        this.date = report.getDate();
        this.diseaseName = report.getKcdCode().getName();
        this.doctorName = report.getDoctor().getInfo().getName();
        this.major = report.getDoctor().getMajor().getDescription();
        for (Prescription prescription : report.getPrescriptions()) {
            this.prescriptions.add(new PrescriptionDto(prescription));
        }
    }
}
