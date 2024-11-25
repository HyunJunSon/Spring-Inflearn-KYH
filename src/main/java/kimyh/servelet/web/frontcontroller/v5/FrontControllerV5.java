package kimyh.servelet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.servelet.web.frontcontroller.ModelView;
import kimyh.servelet.web.frontcontroller.MyView;
import kimyh.servelet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import kimyh.servelet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import kimyh.servelet.web.frontcontroller.v4.controller.MemberListControllerV4;
import kimyh.servelet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import kimyh.servelet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import kimyh.servelet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import org.springframework.boot.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.XMLFormatter;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMappingMap();
        initHandlerAdapters();

    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object handelr = getHandelr(req);
        if (handelr == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter adapter = getHandlerAdapter(handelr);
        ModelView mv = adapter.handle(req, resp, handelr);

        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private MyHandlerAdapter getHandlerAdapter(Object handelr) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handelr)) {
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handelr);
    }

    private Object getHandelr(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }
}
