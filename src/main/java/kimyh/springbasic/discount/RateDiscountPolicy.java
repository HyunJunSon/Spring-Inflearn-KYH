package kimyh.springbasic.discount;

import kimyh.springbasic.Grade;
import kimyh.springbasic.annotation.MainDiscountPolicy;
import kimyh.springbasic.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 20;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }
        return 0;
    }
}
