package kimyh.springbasic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class ParameterNameMatchingTest {

    @Test
    @DisplayName("타입 매칭 후, 여러 빈이 있으면 파라미터 이름으로 추가 매칭")
    void parameterNameMatchingTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // TestService를 빈으로 가져와서 확인
        TestService testService = ac.getBean(TestService.class);

        // MemoryMemberRepository 빈이 주입되었는지 확인
        Assertions.assertThat(testService.getMemberRepository())
                .isInstanceOf(MemoryMemberRepository.class);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public MemberRepository memoryMemberRepository() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memoryMemberRepository2() {
            return new MemoryMemberRepository2();
        }

        @Bean
        public TestService testService(MemberRepository memoryMemberRepository) {
            // 파라미터 이름 "memoryMemberRepository"를 통해 특정 빈을 매칭
            return new TestService(memoryMemberRepository);
        }
    }

    static class TestService {
        private final MemberRepository memberRepository;

        @Autowired
        public TestService(MemberRepository memoryMemberRepository) {
            this.memberRepository = memoryMemberRepository;
        }

        public MemberRepository getMemberRepository() {
            return memberRepository;
        }
    }

    interface MemberRepository {
    }

    static class MemoryMemberRepository implements MemberRepository {
    }

    static class MemoryMemberRepository2 implements MemberRepository {
    }
}