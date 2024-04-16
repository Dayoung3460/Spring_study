package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
// @SpringBootTest : 스프링 컨테이너와 테스트 함께 실행
@SpringBootTest
// @Transactional : db에 insert query 한 다음에 테스트 했던걸 롤백해줌. 반복 테스트 가능하도록 해줌.
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void exceptionDuplicatedMember() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // assertThrows에서 param2 람다를 태울 때 param1이 터져야 함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("existed member");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("existed member");
//        }

        // then
    }
}