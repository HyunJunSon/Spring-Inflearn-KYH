package kimyh.springmvc12.basic.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.springmvc12.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 넣으면, view 조회 x
     */

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("ok");
    }

    /**
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * @ResponseBody 추가
     * - view 조회 무시하고, HTTP message body에 직접 해당 내용 입력
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
        @RequestParam("username") String memberName,
        @RequestParam("age") int memberAge
    ){
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /**
     * @RequestParam 사용
        파라미터 이름이 변수명과 같으면 생략가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * String, int 등의 단순타입이면 @RequestParam 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam required=true
     * - 파라미터가 없으면 400 에러
     * - 기본값 설정 가능
     * 주의! /request-param-required?username= -> 빈문자로 통과된다.
    주의!
     * /request-param-required
     * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는
    defaultValue 사용)
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam defaultValue
     * - 파라미터가 없으면 기본값으로 설정
     * - 기본값 설정 가능
     * 주의! /request-param-required?username= -> 빈문자로 통과된다.
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam nullable
     * - 파라미터가 없으면 null로 설정
     * - 기본값 설정 가능
     * 주의! /request-param-required?username= -> 빈문자로 통과된다.
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam(required = true) String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap 사용
     * - 파라미터가 없으면 null로 설정
     * - 기본값 설정 가능
     * 주의! /request-param-required?username= -> 빈문자로 통과된다.
     */
    @ResponseBody
    @RequestMapping("/request-param-map2")
    public String requestParamMap2(
            @RequestParam MultiValueMap<String, Object> paramMap
    ){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        log.info("paramMap cnt={}", paramMap.size());
        return "ok";
    }

    /**
     * @ModelAttribute 사용
     * - 파라미터가 없으면 null로 설정
     * - 기본값 설정 가능
     * 주의! /request-param-required?username= -> 빈문자로 통과된다.
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(
            @ModelAttribute HelloData helloData
    ){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


    /**
     * @ModelAttribute 생략 가능
     * String, int 등의 단순타입이면 @RequestParam 생략 가능
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(
            HelloData helloData
    ){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
