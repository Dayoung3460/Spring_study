package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// Repository의 구현체(MemoryMemberRepository)에 @Repository 달아줘야함
// 정향화된 구조임
// 컨트롤러: 외부요청 받고, 서비스: 비지니스 로직 만들고, 리파지토리: 데이터 저장하고

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    // key 값 생성해줌
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null이 반환될 가능성이 있으면 Optional.ofNullable로 감쌈
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // findAny(): 하나라도 찾는 메소드
        //하나도 없으면 Optional에 null이 감싸져서 반환됨
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
