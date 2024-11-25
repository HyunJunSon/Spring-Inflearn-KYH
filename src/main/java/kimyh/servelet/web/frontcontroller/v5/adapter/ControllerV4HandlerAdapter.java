package kimyh.servelet.web.frontcontroller.v5.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.servelet.web.frontcontroller.ModelView;
import kimyh.servelet.web.frontcontroller.v4.ControllerV4;
import kimyh.servelet.web.frontcontroller.v5.MyHandlerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {


    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {

        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createPramMap(req);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);

        return modelView;
    }

    private Map<String, String> createPramMap(HttpServletRequest req) {
        HashMap<String, String> map = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> map.put(paramName, req.getParameter(paramName)));
        return map;
    }

}
