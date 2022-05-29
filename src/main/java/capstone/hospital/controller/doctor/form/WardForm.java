package capstone.hospital.controller.doctor.form;

import capstone.hospital.domain.enumtype.WardType;
import lombok.Data;

@Data
public class WardForm {

    private Long reportId;
    private WardType type;
}
