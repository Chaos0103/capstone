package capstone.hospital.controller.admin;

import capstone.hospital.controller.admin.form.EslJsonForm;
import capstone.hospital.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/esl/api")
public class EslApiController {

    private final RestTemplateService templateService;

    @ResponseBody
    @PostMapping
    public EslJsonForm sendDoctorEsl(@RequestBody final EslJsonForm form) {
        log.info("form={}", form);
        EslJsonForm post = templateService.post(form);
        return post;
    }
}
