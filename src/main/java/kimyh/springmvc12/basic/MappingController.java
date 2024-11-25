package kimyh.springmvc12.basic;

import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본허용
     * 둘다 허용 /hello-basic, /hello-basic/
     * HTTP 메서드 모두 허용 GET,HEAD,POST,PUT,PATCH,DELETE
     */
    @RequestMapping({"/hello-basic" , "/hello-basic/"})
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     *  편리한 축약 어노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명 같으면 생략가능
     * @PathVariable("userId") String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug", "data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "!mode")
    public String mappingParam(@Nullable @RequestParam String mode) {
        log.info("mappingParam");
        log.info("mode={}", mode);
        return "ok";
    }
    /**
     * * 특정 헤더로 추가 매핑
     * * headers="mode",
     * * headers="!mode"
     * * headers="mode=debug"
     * * headers="mode!=debug" (! = )
     * */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * produces="text/html"
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes(@RequestBody Map<String, Object> payload) {
        log.info("mappingConsumes");
        System.out.println("payload.get(\"hi\") = " + payload.get("hi"));
        return "ok";
    }

    /**
     * Accept 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * produces="text/html"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

    /**
     * @RequestMapping 사용
     * 둘다 허용 /hello-basic, /hello-basic/
     */
}
