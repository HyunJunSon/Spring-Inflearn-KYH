package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        //세션 생성
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, mockHttpServletResponse);

        //요청에 응답 쿠키 저장
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setCookies(mockHttpServletResponse.getCookies());

        //세션조회
        Object result = sessionManager.getSession(mockHttpServletRequest);
        Assertions.assertThat(result).isEqualTo(member);

        //세션만료
        sessionManager.expire(mockHttpServletRequest);
        Object expiredSession = sessionManager.getSession(mockHttpServletRequest);
        Assertions.assertThat(expiredSession).isNull();
    }
}
