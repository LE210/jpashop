package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository repository;

    @Test
    @Transactional
    public void save() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = repository.save(member);
        Member findMember = repository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    void find() {
    }

}