package capstone.hospital.domain.enumtype;

public enum DoctorRoom {
    ROOM1("1001"), ROOM2("1002");

    private final String description;

    DoctorRoom(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
