package kimyh.servelet.web.frontcontroller.v1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.servelet.domain.member.Member;
import kimyh.servelet.domain.member.MemberRepository;
import kimyh.servelet.web.frontcontroller.v1.ControllerV1;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members",members);

        String viewPath = "/WEB-INF/views/members.jsp";
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
