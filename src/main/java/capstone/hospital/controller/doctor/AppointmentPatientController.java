package capstone.hospital.controller.doctor;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.domain.Doctor;
import capstone.hospital.dto.AppointmentDto;
import capstone.hospital.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor/appointment")
public class AppointmentPatientController {

    private final DoctorService doctorService;

    @GetMapping
    public String appointmentList(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("appointments", doctorService.findAppointment(((Doctor) loginMember).getId()));
        return "/doctor/appointment/appointmentList";
    }
}
