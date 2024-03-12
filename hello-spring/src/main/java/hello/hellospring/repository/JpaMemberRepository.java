package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    /*
    build.gradle 에서 spring-boot-starter-data-jpa 받음으로써
    스프링 부트가 자동으로 EntityManager를 생성되어 짐
    따라서 JPA를 사용하기 위해 EntityManager를 주입 받아서 사용한다.
     */
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //JPQL 언어 사용
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
