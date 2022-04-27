package capstone.hospital.domain.enumtype;

public enum DoctorRank {
    INTERN("인턴"), RESIDENT("레지던트"), FELLOW("펠로우"), PROFESSOR("교수");
    // 의대생, 인턴, 레지던트, 펠로우(임상전임강사), 교수

    private final String description;

    DoctorRank(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
