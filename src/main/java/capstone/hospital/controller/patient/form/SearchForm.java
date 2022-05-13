package capstone.hospital.controller.patient.form;

import capstone.hospital.domain.enumtype.Major;
import lombok.Data;

@Data
public class SearchForm {

    private Major major;
    private String doctorName;

}
