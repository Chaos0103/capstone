package capstone.hospital.controller.admin.form;

import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import lombok.Data;

@Data
public class EslForm {

    private String name;
    // 진료과
    private Major major;
    // 직급
    private DoctorRank rank;
    // QR
}
