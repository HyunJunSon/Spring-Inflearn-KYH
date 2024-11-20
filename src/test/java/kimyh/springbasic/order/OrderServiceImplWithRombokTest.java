package kimyh.springbasic.order;

import kimyh.springbasic.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplWithRombokTest {

    @Test
    @DisplayName("Lombok annotation은 생성자를 자동으로 만든다. 이때 기본은 null을 허용한다. @NonNull에 null을 넣으면 에러발생")
    void requiredArgsContructorTest() {

        assertThatThrownBy(() -> new OrderServiceImplWithRombok(null, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("@NoneNull 에 값을 넣어주면 정상 작동한다.")
    void requiredArgsContructorTest2() {

        OrderServiceImplWithRombok orderService = new OrderServiceImplWithRombok(new MemoryMemberRepository(), null);

        assertThat(orderService).isInstanceOf(OrderService.class);
    }
}