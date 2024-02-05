package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // enum 사용시 꼭 넣어줘야함, 오리지널 쓰면 망함 ex) COMP가 밀려서 3번이되면 XXX 이런식으로 뜬다
    private DeliveryStatus status; // READY, COMP
}
