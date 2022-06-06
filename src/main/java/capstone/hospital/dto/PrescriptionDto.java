package capstone.hospital.dto;

import capstone.hospital.controller.doctor.form.PrescriptionForm;
import capstone.hospital.domain.Prescription;
import lombok.Data;

@Data
public class PrescriptionDto {

    private Long id;

    private String medicineName;
    private String medicineCode;

    private String singleDose;
    private String dailyDose;
    private String totalDoseDays;

    public PrescriptionDto(PrescriptionForm form) {
        this.medicineName = form.getMedicineName();
        this.medicineCode = form.getMedicineCode();
        this.singleDose = form.getSingleDose();
        this.dailyDose = form.getDailyDose();
        this.totalDoseDays = form.getTotalDoseDays();
    }

    public PrescriptionDto(Prescription data) {
        this.id = data.getId();
        this.medicineName = data.getAtcCode().getName();
        this.medicineCode = data.getAtcCode().getCode();
        this.singleDose = data.getSingleDose();
        this.dailyDose = data.getDailyDose();
        this.totalDoseDays = data.getTotalDoseDays();
    }

    public PrescriptionDto() {
    }
}
