package capstone.hospital.controller.nurse.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HomeForm {

    private LocalDateTime date;
    private int totalInpatient;
    private int todayInpatient;

    private String name;
    private LocalDateTime inject;

    private String medicineCode;

    // 투약상태

}
