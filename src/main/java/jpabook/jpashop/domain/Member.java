package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded // 내장이 되었다. 라는 표현식 둘중에 하나만 있어도 되지만 두개 다 해주는 방식도 많다.
    private Address address;

    @JsonIgnore // 양방향관계에서 무한루프에 빠지지 않으려면 한쪽을 끊어줘야한다.
    @OneToMany(mappedBy = "member") // order 에 있는 member, 여기서 값을 바꾼다고 값이 변경되지 않음 order 테이블에서 변경해야 바뀜
    private List<Order> orders = new ArrayList<>();
}
