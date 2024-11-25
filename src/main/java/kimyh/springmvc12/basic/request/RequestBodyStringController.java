package kimyh.springmvc12.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    /**
     * InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer) : HTTP 응답 메시지 바디의 내용을 직접 조회
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity : HTTP header, body 정보를 편리하게 조회
     * - 메시지 바디 정보를 직접 조회 (@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 (StringHttpMessageConverter 사용)
     *
     * 응답에도 HTTPEntity 사용가능
     * - 메시지 바디 정보 직접 변환(view 조회x)
     * - HttpMessageConverter 사용 (StringHttpMessageConverter 사용)
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");
    }

    /**
     * @RequestBody
     * - 메시지 바디 정보를 직접 조회한다.(@RequestParam X, @ModelAttribute X)
     * - Converter 사용
     * - HttpMessageConverter 사용 (StringHttpMessageConverter 사용)
     *
     * 응답에도 @ResponseBody 사용가능
     * - 메시지 바디 정보 직접 변환(view 조회x)
     * - HttpMessageConverter 사용 (StringHttpMessageConverter 사용)
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
