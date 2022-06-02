package capstone.hospital.controller.doctor;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.domain.Doctor;
import capstone.hospital.dto.AppointmentDto;
import capstone.hospital.service.AppointmentService;
import capstone.hospital.service.DoctorService;
import capstone.hospital.service.InpatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorHomeController {

    private final InpatientService inpatientService;
    private final AppointmentService appointmentService;

    @GetMapping
    public String home(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("today", LocalDateTime.now());
        model.addAttribute("myInpatient", inpatientService.myInpatient(((Doctor) loginMember).getId()));
        model.addAttribute("inpatients", inpatientService.myInpatientInfo(((Doctor) loginMember).getId()));
        model.addAttribute("myAppointment", appointmentService.myAppointment(((Doctor) loginMember).getId()));
        model.addAttribute("appointments", appointmentService.myAppointmentInfo(((Doctor) loginMember).getId()));

        return "/doctor/home";
    }

}
