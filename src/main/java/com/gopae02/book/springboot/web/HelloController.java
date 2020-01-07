package com.gopae02.book.springboot.web;

import com.gopae02.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어준다.
public class HelloController {

    @GetMapping("/hello")   // HTTP Method인 Get의 요청을 받을수 있는 API를 만들어준다.
    public String hello() {
        return "hello";  // hello요청이 오면 hello 반환하도록
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
    // RequestParam: 외부에서 api로 넘긴 파리미터를 가져오는 어노테이션
}
