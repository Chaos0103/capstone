package capstone.hospital.controller.patient.form;

import lombok.Data;

@Data
public class AppointmentForm {

    private Long doctorId;
    private String date;
    private String time;

}
