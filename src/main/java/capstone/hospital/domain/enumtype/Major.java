package capstone.hospital.domain.enumtype;

public enum Major {
    FM("가정의학과"), IM("내과"), PED("소아청소년과"), PDS("소아외과"), NU("신경과"),
    PY("정신건강의학과"), RM("재활의학과"), DR("피부과"), UR("비뇨의학과"), OBGY("산부인과"),
    PS("성형외과"), NS("신경외과"), EY("안과"), GS("외과"), ENT("이비인후과"), OS("정형외과"),
    CS("흉부외과"), AN("마취통증의학과"), RO("방사선종양학과"), RA("영상의학과"), EM("응급의학과");

    private final String description;

    Major(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
