package kimyh.springbasic.scan.filter;

import kimyh.springbasic.member.MemberRepository;
import kimyh.springbasic.member.MemoryMemberRepository2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        Assertions.assertThatThrownBy(()-> ac.getBean("beanB", BeanB.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);

    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION , classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION , classes = MyExcludeComponent.class)
    )
    static public class ComponentFilterAppConfig {
    }

    @Test
    @DisplayName("수동빈과 자동빈이 충돌할 경우 수동빈이 우선하며, 자동빈을 오버로딩 한다.")
    void 수동빈등록vs자동빈등록() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig2.class);

        MemberRepository memoryMemberRepository = ac.getBean("memoryMemberRepository", MemberRepository.class);
        Assertions.assertThat(memoryMemberRepository).isInstanceOf(MemoryMemberRepository2.class);

    }

    @Configuration
    @ComponentScan(
            excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION , classes = Configuration.class)
    )
    static public class AutoAppConfig2 {

        @Bean(name= "memoryMemberRepository")
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository2();
        }

    }
}
