package capstone.hospital.controller.patient;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.domain.Appointment;
import capstone.hospital.domain.Patient;
import capstone.hospital.repository.AppointmentRepository;
import capstone.hospital.service.AppointmentService;
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
@RequestMapping("/patient")
public class AppointmentListController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointmentList")
    public String appointments(@Login Object loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("appointments", appointmentService.readAppointment(((Patient) loginMember).getId()));
        return "/patient/appointmentList";
    }
}
