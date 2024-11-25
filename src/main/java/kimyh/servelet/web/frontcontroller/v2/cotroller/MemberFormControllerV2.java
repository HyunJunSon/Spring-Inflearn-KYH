package kimyh.servelet.web.frontcontroller.v2.cotroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.servelet.web.frontcontroller.MyView;
import kimyh.servelet.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");

    }
}
