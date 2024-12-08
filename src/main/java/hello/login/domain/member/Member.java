package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Member {

    private Long id;

    @NotNull
    private String loginId; // login ID
    @NotNull
    private String name;
    @NotEmpty
    private String password;
}
