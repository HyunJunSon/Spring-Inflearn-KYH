package kimyh.jdbc.service;


import kimyh.jdbc.domain.Member;
import kimyh.jdbc.repository.MemberRepositoryV1;
import kimyh.jdbc.repository.MemberRepositoryV2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

import static kimyh.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 기본동작, 트랙잭셔니 없어서 문제 발생
 */
public class MemberServiceV2Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberRepositoryV2 memberRepository;
    private MemberService2 memberService;

    @BeforeEach
    void before() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        memberRepository = new MemberRepositoryV2(dataSource);
        memberService = new MemberService2(dataSource, memberRepository);
    }

    @AfterEach
    void after() throws SQLException {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {
        // given
        memberRepository.save(new Member(MEMBER_A, 10000));
        memberRepository.save(new Member(MEMBER_B, 10000));
        // when
        memberService.accountTransfer(MEMBER_A, MEMBER_B, 2000);
        // then
        assertThat(memberRepository.findById(MEMBER_A).getMoney()).isEqualTo(8000);
        assertThat(memberRepository.findById(MEMBER_B).getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    void accountTransferEx() throws SQLException {

        Member member_A = memberRepository.save(new Member(MEMBER_A, 10000));
        Member member_EX = memberRepository.save(new Member(MEMBER_EX, 10000));

        assertThatThrownBy(() -> memberService.accountTransfer(MEMBER_A, MEMBER_EX, 2000))
                .isInstanceOf(IllegalStateException.class);


        Member findMember_A = memberRepository.findById(member_A.getMemberId());
        Member findMember_EX = memberRepository.findById(member_EX.getMemberId());
        assertThat(findMember_A.getMoney()).isEqualTo(10000);
        assertThat(findMember_EX.getMoney()).isEqualTo(10000); // A의 돈만 줄어 들었다.

    }
}
