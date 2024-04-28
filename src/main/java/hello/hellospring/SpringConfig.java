package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// *** Bean: 스프링 컨테이너에 의해 관리되는 재사용 가능한 소프트웨어 컴포넌트.
// = 스프링 컨테이너가 관리하는 인스턴스화된 자바 객체
// 스프링 컨테이너에 등록된 객체 = 스프링 빈
// @Bean 어노테이션 통해 메서드로부터 반화된 객체를 스프링 컨테이너에 등록함.

@Configuration
public class SpringConfig {
    // memberService, memberRepository를 스프링 빈을 등록
    // 스프링 빈에 등록되어 있는 memberRepository을 MemberService에 넣어줌

    private EntityManager em;
    private DataSource dataSource;

    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
