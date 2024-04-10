// service 패키지에서는 비지니스 로직들이 들어감
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    // constructor생성: 외부에서 new MemberService()해서 다른 인스턴스 만들 필요없이
    // 외부에서 memberRepository를 넣어주도록
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    회원가입
    public Long join(Member member) {
        // ifPresent: ~에 값이 있으면(Optional이기 때문에 사용할 수 있음)
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(M -> {
                    throw new IllegalStateException("existed member");
                });
    }

    // 전체 회원 조히
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
