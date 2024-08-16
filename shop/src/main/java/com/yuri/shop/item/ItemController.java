package com.yuri.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        try {
//            Optional<Item> result = itemRepository.findById(id);
//            if (result.isPresent()){
//                // 가져온 데이터 html에 넣기
//                model.addAttribute("data", result.get());
//                return "detail.html";
//            } else {
//                return "redirect:/list";
//            }
            throw new Exception("이런저런에러임");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/list";
        }


    }


    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable Long id) {

        // 수정페이지에 기존내용 채워넣기
        // DB에서 1번 글 가져와서 여기다가 넣기
//        Optional<Item> result = itemRepository.findById(1L);
        Optional<Item> result = itemRepository.findById(id);

        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }

    }


    @PostMapping("/edit")
    String editItem(String title, Integer price, Long id) {
        // 1번 상품 수정기능
//        Item item = new Item();
////        item.setId(1L); // id가 1인 행이 있으면 아래 내용으로 덮어쓰기(수정)
//        item.setId(id);
//        item.setTitle(title); // title - 유저가 보낸 제목으로
//        item.setPrice(price); // price - 유저가 보낸 가격으로
//        itemRepository.save(item); // 수정

        itemService.editItem(title, price, id);
        return "redirect:/list";
    }

//    @PostMapping("/test1")
////    유저가 ajax body로 보낸 데이터 출력은
//    String test1(@RequestBody Map<String, Object> body) {
//        System.out.println(body.get("name"));
//        return "redirect:/list";
//    }

    @GetMapping("/test2")
    String test2(@RequestParam String name) {
        System.out.println(name);
        return "redirect:/list";
    }

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Long id){
        // 서버는 요청받으면 DB에 있던 상품 삭제
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }



}