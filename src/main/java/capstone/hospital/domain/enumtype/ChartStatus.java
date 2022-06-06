package capstone.hospital.domain.enumtype;

public enum ChartStatus {
    INJECTION("투약중"), COMPLETE("투약완료");

    private final String description;

    ChartStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
