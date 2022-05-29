package capstone.hospital.dto;

import capstone.hospital.controller.doctor.form.PrescriptionForm;
import capstone.hospital.domain.Prescription;
import lombok.Data;

@Data
public class PrescriptionDto {

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

    public PrescriptionDto(Prescription form) {
        this.medicineName = form.getAtcCode().getName();
        this.medicineCode = form.getAtcCode().getCode();
        this.singleDose = form.getSingleDose();
        this.dailyDose = form.getDailyDose();
        this.totalDoseDays = form.getTotalDoseDays();
    }
}
