package kimyh.servelet.web.frontcontroller.v2.cotroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimyh.servelet.domain.member.Member;
import kimyh.servelet.domain.member.MemberRepository;
import kimyh.servelet.web.frontcontroller.MyView;
import kimyh.servelet.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        req.setAttribute("members",members);
        return new MyView("/WEB-INF/views/members.jsp");
    }
}
