package capstone.hospital.controller.doctor.form;

import lombok.Data;

@Data
public class PrescriptionForm {

    private String medicineName;
    private String medicineCode;

    private String singleDose;
    private String dailyDose;
    private String totalDoseDays;

}
