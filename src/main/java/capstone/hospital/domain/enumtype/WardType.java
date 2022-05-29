package capstone.hospital.domain.enumtype;

public enum WardType {
    ICU("중환자실"), ER("응급실"), NR("신생아실"), CSR("중앙공급실"), CNU("신경외과 집중 치료실"),
    CCU("심질환 집중 치료실"), PICU("소아중환자실");

    private final String description;

    WardType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

/**
 * ICU(Intensive care unit): 중환자실
 */