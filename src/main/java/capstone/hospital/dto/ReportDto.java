package capstone.hospital.dto;

import capstone.hospital.domain.Report;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportDto {

    private LocalDateTime date;
    private String diseaseName;
    private String doctorName;
    private String major;

    public ReportDto(Report report) {
        this.date = report.getDate();
        this.diseaseName = report.getKcdCode().getName();
        this.doctorName = report.getDoctor().getInfo().getName();
        this.major = report.getDoctor().getMajor().getDescription();
    }
}
