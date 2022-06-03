package capstone.hospital.controller.admin;

import capstone.hospital.controller.admin.form.EslJsonForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/esl/send")
public class EslSendController {

    @ResponseBody
    @PostMapping
    public ResponseEntity<EslJsonForm> sendDoctorEsl(@Validated @RequestBody final EslJsonForm form) {


        return ResponseEntity.ok(form);
    }
}
