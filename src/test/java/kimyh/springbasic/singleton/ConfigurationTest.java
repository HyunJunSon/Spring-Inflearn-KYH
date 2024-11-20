package kimyh.springbasic.singleton;

import kimyh.springbasic.AppConfig;
import kimyh.springbasic.member.MemberRepository;
import kimyh.springbasic.member.MemberServiceImpl;
import kimyh.springbasic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {
    ApplicationContext ac;

    @BeforeEach
    void beforeAll() {
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    @DisplayName("AppConfig 내에서 생성하는 모든 memberRepository는 같은 객체이다.")
    void configurationTest() {
        
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());
        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
