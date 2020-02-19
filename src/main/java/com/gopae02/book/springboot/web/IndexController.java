package com.gopae02.book.springboot.web;

import com.gopae02.book.springboot.config.auth.LoginUser;
import com.gopae02.book.springboot.config.auth.dto.SessionUser;
import com.gopae02.book.springboot.service.PostsService;
import com.gopae02.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    // @LoginUser SessionUser user : 기존 httpSession.getAttibute("user") 방식에서 변경
    // 어느컨트롤러에서든지 @LoginUser만 사용하면 세션 정보를 가져올수 있다.
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}