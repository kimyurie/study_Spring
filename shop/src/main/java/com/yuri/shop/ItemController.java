package com.yuri.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;


    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        var a = new Item();
        System.out.println(a.toString());

        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    // 함수 하나에는 한가지 기능만 담기 권장
    @PostMapping("/add")
    String addPost(String title, Integer price) {
        // 아래 코드 ItemService로 옮기기
//        Item item = new Item();
//        item.setTitle(title);
//        item.setPrice(price);
//        itemRepository.save(item);

        // => service에서 불러와서 사용
        itemService.saveItem(title, price);
        return "redirect:/list";
    }





    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {

        try{
//            Optional<Item> result = itemRepository.findById(id);
//            if (result.isPresent()){
//                // 가져온 데이터 html에 넣기
//                model.addAttribute("data", result.get());
//                return "detail.html";
//            } else {
//                return "redirect:/list";
//            }
            throw new Exception("이런저런에러임");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/list";
        }


    }

}