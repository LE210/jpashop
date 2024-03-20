package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    @Transactional // 트렌젝션으로 인해 커밋됨
    public void updateItem(Long itemId, Book param) {
        Item findItem = itemRepository.findOne(itemId); // 현재는 영속성

        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());
        // 플러시 : 영속성 컨텍스트에서 변경된 부분을 싹 다 찾음
        // 이 상태에서 쿼리를 날려 업데이트 됨
        // 머지를 사용하지 말고 원하는 부분만 변경감지 사용하는게 좋다, 잘못하면 null 값이 박힌다.
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
