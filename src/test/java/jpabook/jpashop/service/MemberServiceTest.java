package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional  // 테스트 케이스에 있으면! 기본적으로 롤백을 하기 때문에 insert 문이 나가지 않는다 (쿼리가 보이지 않음 콘솔에)
                // 정확하게는 영속성 컨텍스트가 플러쉬를 하지 않음
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 가입() throws Exception {

        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long saveId = memberService.join(member);


        // then
        em.flush(); // 쿼리문을 보고 싶을 때
        assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test
    public void 중복제외() throws Exception {

        // given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        // when

        try {
            memberService.join(member1);
            memberService.join(member2); //예외가 발생 해야 한다.

        } catch (IllegalStateException e) {
            return;
        }

        // then
        fail("예외 발생해야하는데?");

    }

    @Test
    void join() {

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }


}