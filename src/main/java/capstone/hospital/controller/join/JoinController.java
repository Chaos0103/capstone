package capstone.hospital.controller.join;

import capstone.hospital.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinController {

    private final JoinService joinService;

    // Mapping
    @GetMapping
    public String joinSelect() {
        return "join/userJobSelect";
    }

    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("name", (String) model.asMap().get("name"));
        return "join/joinSuccess";
    }

//    @PostMapping("/loginIdCheck")
//    @ResponseBody
//    public String idCheck(@RequestBody String loginId, HttpSession session) {
//        log.info("id={}", loginId);
//        try {
//            JSONObject json = new JSONObject(loginId);
//            String id = json.getString("loginId");
//            log.info("id={}", id);
//            boolean sdd = joinService.validateDuplicateLoginId(id);
//            log.info("data={}", sdd);
//
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//
//        return "ok";
//    }
}
