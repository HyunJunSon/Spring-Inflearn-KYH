package kimyh.jdbc.repository;

import kimyh.jdbc.domain.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {

        Member member = new Member("memberV0", 10000);
        Member saved = repository.save(member);
    }
}
