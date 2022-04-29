package capstone.hospital.controller;

import capstone.hospital.form.PhoneCheckForm;
import capstone.hospital.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Random;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ValidateController {

    private final ValidateService validateService;

    @GetMapping("/check/sendSMS")
    public String sendSMS(@ModelAttribute("phone") PhoneCheckForm form) {
        log.info("form={}", form);

        String phoneNumber = form.getPhoneNumberFront() + form.getPhoneNumberMid() + form.getPhoneNumberBack();

        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        System.out.println("수신자 번호 = " + phoneNumber);
        System.out.println("인증 번호 = " + numStr);
        validateService.sendMsg(phoneNumber, numStr);
        return "redirect:/";

    }
}
