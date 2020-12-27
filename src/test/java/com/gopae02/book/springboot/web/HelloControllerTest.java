package com.gopae02.book.springboot.web;

import com.gopae02.book.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)    // 테스트를  진행할때, junit에 내장된 실행자외 다른 실행자를 실행.
// 여기서는 springrunner 실행자 사용, 즉. 스프링 부트 테스트와 junit 사이에 연결자 역할
// @WebMvcTest // springmvc 에 집중할수잇는 테스트 어노테이션선언
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired  // 스프링이 관리하는 빈Bean 주입받음
    private MockMvc mvc;    // 웹 api 테스트시 사용. 스프링 mvc 테스트의 시작점.. http get, post 테스트 가능

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))  // mockMvc를 통해 /hello 주소로 http get 요청. 체이닝 지원
                .andExpect(status().isOk())     // mvc.perform 결과 검증, status 검증.  200, 404, 500 등.
                .andExpect(content().string(hello));    // mvc perform 결과 검증. 응답 본문의 내용 검증., cotroller에서 hello 리턴한거 검증한다
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
    // param: api 테스트를 할때 사용될 요청 파라미터 설정, String만 사용 가능. 숫자,날짜도 변환해서 써야함.
    // jsonPath: json응답 값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시
}
