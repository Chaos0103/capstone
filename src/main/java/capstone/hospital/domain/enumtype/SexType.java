package capstone.hospital.domain.enumtype;

public enum SexType {
    MALE("남성"), FEMALE("여성");

    private final String description;

    SexType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
