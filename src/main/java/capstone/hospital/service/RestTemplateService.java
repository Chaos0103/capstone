package capstone.hospital.service;

import capstone.hospital.controller.admin.form.EslJsonForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    public EslJsonForm post(EslJsonForm form) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://192.168.1.140:8080")
                .path("/esl/merchandise/v2/edit")
                .encode()
                .build()
                .expand()
                .toUri();
        System.out.println(uri);

        RestTemplate restTemplate = new RestTemplate();
        System.out.println("===============");
        ResponseEntity<EslJsonForm> response = restTemplate.postForEntity(uri, form, EslJsonForm.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }
}
