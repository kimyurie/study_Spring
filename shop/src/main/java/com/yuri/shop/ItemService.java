package com.yuri.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 비즈니스 로직 담는 클래스는 Service라고 부름
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

}
