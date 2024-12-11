package kimyh.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
public class ServletExController {

    @GetMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("예외 발생 !");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404, "404 오류");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }

//    @GetMapping("/error-page/404")
    public String errorpage404(HttpServletResponse response) throws IOException {
        log.info("errorPage 404");
        return "error-page/404";
    }

//    @GetMapping("/error-page/500")
    public String errorpage500(HttpServletResponse response) throws IOException {
        log.info("errorPage 500");
        return "error-page/500";
    }
}
