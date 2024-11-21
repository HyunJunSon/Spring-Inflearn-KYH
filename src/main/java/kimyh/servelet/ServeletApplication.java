package kimyh.servelet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ServeletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServeletApplication.class, args);
    }

}
