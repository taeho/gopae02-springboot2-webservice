package com.gopae02.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        // env.getActiveProfiles: 현재 실행중인 Active Profile을 모두 가져온다.
        // 즉, real, oauth, real-db 등이 활성화 되어 있다면 Active면 3개가 모두 담겨있다.
        // 여기서 real, real1, real2 는 모두 배포에 사용될 profile이라 이 중하나라도 있으면 값 반환.
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
