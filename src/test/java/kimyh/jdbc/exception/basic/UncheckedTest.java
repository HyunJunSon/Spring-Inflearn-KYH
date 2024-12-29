package kimyh.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckedTest {

    @Test
    void unchecked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyCheckedException.class);
    }

    @Test
    void unchekced_catch() {
        Service service = new Service();
        service.callCatch();
    }

    /**
     * Uncheked 예외는 예외를 잡거나, 던지지 않아도 된다.
     * 예외를 잡지 않으면 자동으로 밖으로 던진다.
     */
    static class Service{
        Repository repository = new Repository();
        /**
         * 필요한 경우 예외를 잡아서 처리하면 된다.
         */
        public void callCatch(){
            try{
                repository.call();
            }catch (MyCheckedException e){
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        /***
         * 예외를 잡지 않아도 자연스럽게 상위로 넘어간다.
         * 체크예외와 달리 throws 예외선언 안해도 된다.
         */
        public void callThrow()  {
            repository.call();
        }
    }


    static class Repository{
        public void call() {
            throw new MyCheckedException("ex");
        }
    }

    static class MyCheckedException extends RuntimeException {
        public MyCheckedException(String message) {
            super(message);
        }
    }
}
