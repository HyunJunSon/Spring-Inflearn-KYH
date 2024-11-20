package kimyh.springbasic.order;

import kimyh.springbasic.discount.DiscountPolicy;
import kimyh.springbasic.member.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceImplWithRombok implements OrderService{
    @NonNull
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        return null;
    }
}
