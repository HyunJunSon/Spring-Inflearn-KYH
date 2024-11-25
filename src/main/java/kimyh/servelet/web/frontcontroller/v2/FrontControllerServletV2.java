package kimyh.servelet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.servelet.web.frontcontroller.MyView;
import kimyh.servelet.web.frontcontroller.v2.cotroller.MemberFormControllerV2;
import kimyh.servelet.web.frontcontroller.v2.cotroller.MemberListControllerV2;
import kimyh.servelet.web.frontcontroller.v2.cotroller.MemberSaveControllerV2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerServletV2() {
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerV2 Start");
        String requestURI = req.getRequestURI();
        ControllerV2 controllerV2 = controllerV2Map.get(requestURI);
        if (controllerV2 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView myView = controllerV2.process(req, resp);
        myView.render(req, resp);
    }
}
