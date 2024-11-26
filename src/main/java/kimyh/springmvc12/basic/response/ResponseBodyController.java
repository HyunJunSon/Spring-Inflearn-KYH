package kimyh.springmvc12.basic.response;


import jakarta.servlet.http.HttpServletResponse;
import kimyh.springmvc12.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {

        response.getWriter().write("ok");
    }

    /**
     * HttpEntity
     * ResponseEntity (Http Status 추가)
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(HttpServletResponse response) throws IOException {
        
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(HttpServletResponse response) throws IOException {

        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() throws IOException {

        HelloData user = new HelloData();
        user.setUsername("kim");
        user.setAge(20);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() throws IOException {

        HelloData user = new HelloData();
        user.setUsername("kim");
        user.setAge(20);

        return user;
    }

}
