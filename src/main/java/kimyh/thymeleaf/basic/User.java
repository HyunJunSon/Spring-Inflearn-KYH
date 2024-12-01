package kimyh.thymeleaf.basic;

import lombok.Data;

@Data
public class User {
    private String username;
    private int age;

    public User(String userA, int i) {
        this.username = userA;
        this.age = i;
    }
}
