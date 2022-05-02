package capstone.hospital.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;

import java.util.HashMap;
import java.util.Random;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ValidateService {

    public String sendSMS(String phoneNumber) {

        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        log.info("수신자 번호 = {}", phoneNumber);
        log.info("인증 번호 = {}", numStr);
        sendMsg(phoneNumber, numStr);
        return numStr;
    }

    public void sendMsg(String phoneNumber, String cerNum) {

        String api_key = "NCS3QYCCUYRSJNSQ";
        String api_secret = "8MV5S03PIOWEZ55PQNYPDZYEHSINTU76";
        String mainPhoneNumber = "01084725498";
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);
        params.put("from", mainPhoneNumber);
        params.put("type", "SMS");
        params.put("text", "[세종대병원] 인증번호는" + "[" + cerNum + "]" + "입니다.");
        params.put("app_version", "test app 1.2");

        System.out.println("params = " + params);
        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println("toString" + obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
