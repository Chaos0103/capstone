package capstone.hospital.controller.doctor.form;

import capstone.hospital.dto.DiseaseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportForm {

    private String name;
    private String code;
    private String content;

    public void input(DiseaseDto data) {
        this.name = data.getName();
        this.code = data.getCode();
    }

    public void input(ReportForm data) {
        this.name = data.getName();
        this.code = data.getCode();
        this.content = data.getContent();
    }
}
