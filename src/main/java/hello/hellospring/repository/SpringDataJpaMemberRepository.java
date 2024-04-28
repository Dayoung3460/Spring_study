package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository에 기본적인 CRUD가 다 구현되어 있음. 가져다 쓰면 됨ㅎㅎ
// 페이징 기능도 자동 제공
// 기본은 jpa + 스크링 데이터 jpa 사용하고
// 복잡한 동적 쿼리는 Querydsl이라는 라이브러리 사용하면 됨
// 아니면 걍 JPA가 제공하는 네이티브 쿼리(쌩 sql) 사용하거나 Jdbc template 사용하면됨
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // findByName: JPQL로 "select m from Member m where m.name = ?" 이렇게 짜지고 sql로 변형시켜줌
    // 메서드 이름만으로 개발이 됨
    @Override
    Optional<Member> findByName(String name);
}
