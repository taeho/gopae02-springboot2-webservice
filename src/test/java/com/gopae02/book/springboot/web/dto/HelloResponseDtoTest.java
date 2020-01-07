package com.gopae02.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
// assertj가 JUNIT 보다좋은건
// 1. CoreMatcher와 달리 추가 라이브러리 필요없다.
// 2. 자동완성이 좀더 확실히지원

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        // assertj라는 테스트 검증 라이브러리의 검증 메소드
        //  검증하고 싶은 메소드를 인자로 받는다.
        // 메소드 체이닝이 지원, isEqualTo와 같이 메소드 이어서 사용
        assertThat(dto.getAmount()).isEqualTo(amount);
        // assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을때만 성공
    }

}
