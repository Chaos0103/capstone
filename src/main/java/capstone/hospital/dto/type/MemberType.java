package capstone.hospital.dto.type;

public enum MemberType {
    PATIENT("일반"), DOCTOR("의사"), NURSE("간호사"), ADMIN("관리자");

    private final String description;

    MemberType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
