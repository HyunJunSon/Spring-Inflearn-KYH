package kimyh.servelet.web.frontcontroller.v3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.servelet.web.frontcontroller.ModelView;
import kimyh.servelet.web.frontcontroller.MyView;
import kimyh.servelet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import kimyh.servelet.web.frontcontroller.v3.controller.MemberListControllerV3;
import kimyh.servelet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);
        if (Objects.isNull(controllerV3)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(req);
        ModelView modelView = controllerV3.process(paramMap);

        String viewName = modelView.getViewName();
        MyView myView = viewResolver(viewName);
        myView.render(modelView.getModel(), req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        HashMap<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

}
