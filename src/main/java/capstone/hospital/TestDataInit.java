package capstone.hospital;

import capstone.hospital.domain.*;
import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
import capstone.hospital.dto.UploadFile;
import capstone.hospital.repository.ATCCodeRepository;
import capstone.hospital.repository.KCDCodeRepository;
import capstone.hospital.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final JoinService joinService;
    private final KCDCodeRepository kcdCodeRepository;
    private final ATCCodeRepository atcCodeRepository;

    @PostConstruct
    public void init() throws Exception {
        Address address = new Address("서울특별시", "광진구", "12345");

        initPatient(address);
        initDoctor(address);
        initNurse(address);
        initAdmin(address);
        ATCCode test = new ATCCode("test-01", "타이레놀", "세종대",null, 100);
        KCDCode code = new KCDCode("COVID-19", "코로나19");
        kcdCodeRepository.save(code);
        atcCodeRepository.save(test);
    }

    private void initPatient(Address address) throws Exception {
        Address testAddress = new Address("인천광역시 남동구 석산로 123", "101동 101호", "12345");
        Information info = new Information("세종대", "010101", "1000000", "01000000000", address);
        Patient patient = new Patient("test", "test!", info);

        Information myInfo = new Information("임우택", "980103", "1234567", "01084725498", testAddress);
        Patient my = new Patient("lyt1228", "pw!", myInfo);

        joinService.joinPatient(patient);
        joinService.joinPatient(my);
    }

    private void initDoctor(Address address) throws Exception {
        Information info1 = new Information("이익준", "990101", "1000000", "01000000001", address);
        Information info2 = new Information("채송화", "990102", "2000000", "01000000002", address);
        Information info3 = new Information("김준환", "990103", "1000000", "01000000003", address);
        Information info4 = new Information("안정원", "990104", "1000000", "01000000004", address);
        Information info5 = new Information("양석형", "990105", "1000000", "01000000005", address);

        Information info6 = new Information("도재학", "990106", "1000000", "01000000006", address);
        Information info7 = new Information("허선빈", "990107", "2000000", "01000000007", address);
        Information info8 = new Information("용석민", "990108", "1000000", "01000000008", address);
        Information info9 = new Information("장겨울", "990109", "2000000", "01000000009", address);
        Information info10 = new Information("추민하", "990110", "2000000", "01000000010", address);

        Doctor doctor1 = new Doctor("doctor1", "1234", "서울대학교", info1, "23124", Major.GS, DoctorRank.PROFESSOR, new UploadFile("/file/doctor1.png", "/file/doctor1.png"));
        Doctor doctor2 = new Doctor("doctor2", "1234", "서울대학교", info2, "21421", Major.NS, DoctorRank.PROFESSOR, new UploadFile("/file/doctor2.png", "/file/doctor2.png"));
        Doctor doctor3 = new Doctor("doctor3", "1234", "서울대학교", info3, "53524", Major.CS, DoctorRank.PROFESSOR, new UploadFile("/file/doctor3.png", "/file/doctor3.png"));
        Doctor doctor4 = new Doctor("doctor4", "1234", "서울대학교", info4, "43251", Major.PDS, DoctorRank.PROFESSOR, new UploadFile("/file/doctor4.png", "/file/doctor4.png"));
        Doctor doctor5 = new Doctor("doctor5", "1234", "서울대학교", info5, "34789", Major.OBGY, DoctorRank.PROFESSOR, new UploadFile("/file/doctor5.png", "/file/doctor5.png"));

        Doctor doctor6 = new Doctor("doctor6", "1234", "서울대학교", info6, "21345", Major.CS, DoctorRank.FELLOW, new UploadFile("/file/doctor6.png", "/file/doctor6.png"));
        Doctor doctor7 = new Doctor("doctor7", "1234", "서울대학교", info7, "43256", Major.NS, DoctorRank.FELLOW, new UploadFile("/file/doctor7.png", "/file/doctor7.png"));
        Doctor doctor8 = new Doctor("doctor8", "1234", "서울대학교", info8, "12365", Major.NS, DoctorRank.FELLOW, new UploadFile("/file/doctor8.png", "/file/doctor8.png"));
        Doctor doctor9 = new Doctor("doctor9", "1234", "서울대학교", info9, "42363", Major.GS, DoctorRank.FELLOW, new UploadFile("/file/doctor9.png", "/file/doctor9.png"));
        Doctor doctor10 = new Doctor("doctor10", "1234", "서울대학교", info10, "43263", Major.OBGY, DoctorRank.FELLOW, new UploadFile("/file/doctor10.png", "/file/doctor10.png"));

        joinService.joinDoctor(doctor1);
        joinService.joinDoctor(doctor2);
        joinService.joinDoctor(doctor3);
        joinService.joinDoctor(doctor4);
        joinService.joinDoctor(doctor5);
        joinService.joinDoctor(doctor6);
        joinService.joinDoctor(doctor7);
        joinService.joinDoctor(doctor8);
        joinService.joinDoctor(doctor9);
        joinService.joinDoctor(doctor10);
    }

    private void initNurse(Address address) throws Exception {
        Information info = new Information("송수빈", "700101", "2000000", "01077777777", address);
        Nurse nurse = new Nurse("nurse1", "1234", info, "1", Major.GS);
        joinService.joinNurse(nurse);
    }

    private void initAdmin(Address address) {
        Information info = new Information("admin", "000000", "1000000", "01000000000", address);
        Admin admin = new Admin("admin", "1234", info);
        joinService.joinAdmin(admin);
    }

}
