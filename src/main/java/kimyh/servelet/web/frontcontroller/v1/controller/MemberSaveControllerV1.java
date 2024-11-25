package kimyh.servelet.web.frontcontroller.v1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.servelet.domain.member.Member;
import kimyh.servelet.domain.member.MemberRepository;
import kimyh.servelet.web.frontcontroller.v1.ControllerV1;

import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        request.setAttribute("member", member);
        String viewPath = "/WEB-INF/views/save-result.jsp";
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
