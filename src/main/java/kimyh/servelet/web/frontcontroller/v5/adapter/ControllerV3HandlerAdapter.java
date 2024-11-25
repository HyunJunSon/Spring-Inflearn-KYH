package kimyh.servelet.web.frontcontroller.v5.adapter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.servelet.web.frontcontroller.ModelView;
import kimyh.servelet.web.frontcontroller.v3.ControllerV3;
import kimyh.servelet.web.frontcontroller.v5.MyHandlerAdapter;

import java.util.HashMap;
import java.util.Map;


public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(req);
        ModelView mv = controller.process(paramMap);
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
