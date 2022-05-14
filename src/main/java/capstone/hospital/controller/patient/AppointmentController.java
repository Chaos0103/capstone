package capstone.hospital.controller.patient;

import capstone.hospital.argumentresolver.Login;
import capstone.hospital.controller.patient.form.AppointmentForm;
import capstone.hospital.controller.patient.form.SearchForm;
import capstone.hospital.domain.Patient;
import capstone.hospital.domain.enumtype.Major;
import capstone.hospital.service.AppointmentService;
import capstone.hospital.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class AppointmentController {

    private final SearchService searchService;
    private final AppointmentService appointmentService;

    @ModelAttribute("majors")
    public Major[] majorTypes() {
        return Major.values();
    }

    @GetMapping("/createAppointment")
    public String createAppointment1(@Login Object loginMember, @ModelAttribute("form") SearchForm major, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/patient/createAppointment1";
    }

    @GetMapping("/createAppointment/{major}")
    public String createAppointment2(@Login Object loginMember, @PathVariable Major major,
                               @ModelAttribute("form") AppointmentForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("doctors", searchService.doctorSearchByMajor(major));

        return "/patient/createAppointment2";
    }

    @PostMapping("/createAppointment/{major}")
    public String createAppointment(@Login Object loginMember, @ModelAttribute("form") AppointmentForm form, @PathVariable String major) {
        appointmentService.createAppointment(((Patient) loginMember).getId(), form.getDoctorId(), form.getDate(), form.getTime());
        return "redirect:/patient/appointmentList";
    }

    // 진료 수정
    @GetMapping("/updateAppointment/{appointmentId}/update")
    public String updateAppointment1(@Login Object loginMember, @PathVariable Long appointmentId,
                                    @ModelAttribute("form") AppointmentForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/patient/updateAppointment1";
    }

    @GetMapping("/updateAppointment/{appointmentId}/update/{major}")
    public String updateAppointment2(@Login Object loginMember, @PathVariable Major major,
                                     @ModelAttribute("form") AppointmentForm form, Model model) {
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("doctors", searchService.doctorSearchByMajor(major));
        return "/patient/updateAppointment2";
    }

    @PostMapping("/updateAppointment/{appointmentId}/update/{major}")
    public String updateAppointment(@ModelAttribute("form") AppointmentForm form,
                                    @PathVariable String major, @PathVariable Long appointmentId) {
        appointmentService.updateAppointment(appointmentId, form.getDoctorId(), form.getDate(), form.getTime());
        return "redirect:/patient/appointmentList";
    }

    @GetMapping("/deleteAppointment/{appointmentId}")
    public String deleteAppointment(@Login Object loginMember, @PathVariable Long appointmentId, Model model) {
        model.addAttribute("loginMember", loginMember);
        appointmentService.deleteAppointment(appointmentId);
        return "redirect:/patient/appointmentList";
    }
}
