package kimyh.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(()-> service.callThrow())
                .isInstanceOf(MyCheckedException.class);
    }

    /**
     * Checked 예외는 예외를 잡아서 처리하거나, 던지거나 둘중 하나를 필수로 해야한다.
     */
    static class Service{
        Repository repository = new Repository();
        /**
         * 예외를 잡아서 처리하는 코드
         */
        public void callCatch(){
            try{
                repository.call();
            }catch (MyCheckedException e){
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        /***
         * 체크예외를 밖으로 던지는 코드
         */
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }


    static class Repository{
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }

    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }
}
