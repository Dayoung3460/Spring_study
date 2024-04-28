package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    // jpa는 EntityManager로 모든것이 동작됨
    // 스프링부트가 EntityManager를 알아서 생성해줌(gradle에 jpa 설치하면)
    // 우리는 EntityManager를 인젝션 받기만 하면 됨
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        // persist: 영속하다, 영구 저장하다
        // jpa가 알아서 insert 쿼리 만들어서 db에 넣고 id도 넣어줌
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 조회는 find(조회할 타입, 조회할 식별자)로 하면 됨
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpql : 객체(엔티티, m)를 대상으로 쿼리를 날림 -> sql로 작성해줌
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
