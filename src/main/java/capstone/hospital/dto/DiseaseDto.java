package capstone.hospital.dto;

import capstone.hospital.domain.KCDCode;
import lombok.Data;

@Data
public class DiseaseDto {

    private String code;
    private String name;

    public DiseaseDto(KCDCode data) {
        this.code = data.getCode();
        this.name = data.getName();
    }
}
