package capstone.hospital;

import capstone.hospital.domain.*;
import capstone.hospital.domain.enumtype.DoctorRank;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.domain.valuetype.Address;
import capstone.hospital.domain.valuetype.Information;
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

    @PostConstruct
    public void init() throws Exception {
        Address address = new Address("서울특별시", "광진구", "12345");

        initPatient(address);
        initDoctor(address);
        initNurse(address);
        initAdmin(address);

        KCDCode code = new KCDCode("COVID-19", "코로나19");
        kcdCodeRepository.save(code);
    }

    private void initPatient(Address address) throws Exception {
        Information info = new Information("세종대", "010101", "1000000", "010-1111-1111", address);
        Patient patient = new Patient("test", "test!", info);
        joinService.joinPatient(patient);
    }

    private void initDoctor(Address address) throws Exception {
        Information info1 = new Information("이익준", "990101", "1000000", "010-2222-2222", address);
        Information info2 = new Information("채송화", "990202", "2000000", "010-3333-3333", address);
        Information info3 = new Information("김준환", "990303", "1000000", "010-4444-4444", address);
        Information info4 = new Information("안정원", "990404", "1000000", "010-5555-5555", address);
        Information info5 = new Information("양석형", "990505", "1000000", "010-6666-6666", address);

        Doctor doctor1 = new Doctor("doctor1", "1234", info1, "1", Major.GS, DoctorRank.PROFESSOR);
        Doctor doctor2 = new Doctor("doctor2", "1234", info2, "2", Major.NS, DoctorRank.PROFESSOR);
        Doctor doctor3 = new Doctor("doctor3", "1234", info3, "3", Major.CS, DoctorRank.PROFESSOR);
        Doctor doctor4 = new Doctor("doctor4", "1234", info4, "4", Major.PDS, DoctorRank.PROFESSOR);
        Doctor doctor5 = new Doctor("doctor5", "1234", info5, "5", Major.OBGY, DoctorRank.PROFESSOR);

        joinService.joinDoctor(doctor1);
        joinService.joinDoctor(doctor2);
        joinService.joinDoctor(doctor3);
        joinService.joinDoctor(doctor4);
        joinService.joinDoctor(doctor5);
    }

    private void initNurse(Address address) throws Exception {
        Information info = new Information("송수빈", "700101", "2000000", "010-7777-7777", address);
        Nurse nurse = new Nurse("nurse1", "1234", info, "1", Major.GS);
        joinService.joinNurse(nurse);
    }

    private void initAdmin(Address address) {
        Information info = new Information("admin", "000000", "1000000", "010-0000-0000", address);
        Admin admin = new Admin("admin", "1234", info);
        joinService.joinAdmin(admin);
    }

}
