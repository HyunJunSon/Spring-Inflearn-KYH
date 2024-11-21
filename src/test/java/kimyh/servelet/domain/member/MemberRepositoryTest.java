package kimyh.servelet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import kimyh.servelet.domain.member.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        Member member = new Member("kim", 20);

        Member saveMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findAll() {
        Member memeber1 = new Member("memeber1", 20);
        Member memeber2 = new Member("memeber2", 30);

        memberRepository.save(memeber1);
        memberRepository.save(memeber2);

        List<Member> result =  memberRepository.findAll();
        assertThat(result).contains(memeber1, memeber2);
    }
}