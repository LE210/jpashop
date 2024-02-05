package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//joined 정규화된 스타일, SINGLE_TABLE 한테이블에 다 떄려박기, TABLE_PER_CLASS 3개의 테이블로 나누는 전략
@DiscriminatorColumn(name = "dtype") // 구분할 때 사용 ex) book 이면 어떻게 할래?
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
