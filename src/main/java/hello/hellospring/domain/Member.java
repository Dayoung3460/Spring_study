package hello.hellospring.domain;

import jakarta.persistence.*;

// jpa는 인터페이스고 hibernate 구현기술을 사용할거임
// jpa는 ORM (객체(Object)의 관계(Relationship)을 Mapping한다(어노테이션으로)..?)를 사용
@Entity
public class Member {
    // 쿼리에 id를 넣어주는게 아니고 디비가 자동으로 id 생성해줌 - IDENTITY 전략이라고 함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 디비 컬럼명이 username이면 @Column 어노테이션 사용하면됨
    // ex) @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
