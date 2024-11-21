package kimyh.servelet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;


@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Status-Line
        resp.setStatus(HttpServletResponse.SC_OK);

        //response header
        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello");

        //Header 편의 메서드
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.setContentLength(2);
        coockie(resp);
        resp.sendRedirect("/basic/hello-form.html");
//        resp.getWriter().write("ok");





    }

    private void coockie(HttpServletResponse resp) {
        Cookie cookie = new Cookie("myCookie", "cookieValue");
        // 쿠키 만료 시간 설정 (초 단위)
        cookie.setMaxAge(600); // 600초 = 10분
        // 응답에 쿠키 추가
        resp.addCookie(cookie);

    }
}
