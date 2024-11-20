package kimyh.springbasic;

import kimyh.springbasic.discount.DiscountPolicy;
import kimyh.springbasic.discount.FixDiscountPolicy;
import kimyh.springbasic.member.MemberRepository;
import kimyh.springbasic.member.MemberService;
import kimyh.springbasic.member.MemberServiceImpl;
import kimyh.springbasic.member.MemoryMemberRepository;
import kimyh.springbasic.order.OrderService;
import kimyh.springbasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memeberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new RateDiscountPolicy();
        return new FixDiscountPolicy();
    }

}
