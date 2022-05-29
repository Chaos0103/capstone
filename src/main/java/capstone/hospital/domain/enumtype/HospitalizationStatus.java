package capstone.hospital.domain.enumtype;

public enum HospitalizationStatus {
    WAIT("대기"), Hospitalization("입원중"), OUT("퇴원");

    private final String description;

    HospitalizationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
