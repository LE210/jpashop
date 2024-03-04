package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 스캔에 의해 자동으로 빈으로 관리 된다
@RequiredArgsConstructor
public class MemberRepository {

/*    @PersistenceContext
    private EntityManager em; // 엔티티매니저는 autowired 말고 PersistenceContext 사용해야함, 스프링이 엔티티매니저를 만들어서 주입해줌*/

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member); // persist -> 영속성 컨텍스트에 넣은후 트렌젝션이 일어날때 디비에 저장이됨
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // jpql 문법 Member 엔티티 멤버를 조회 하라는 것
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
