package com.gopae02.book.springboot.web.domain.posts;

import com.gopae02.book.springboot.domain.posts.Posts;
import com.gopae02.book.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After  // Junit에서 단위 테스트가 끝날때 마다 수행되는 메소드를 지정
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title ="테스트 게시글";
        String content = "테스트 본문";

        // postsRepository.save= 테이블posts에 insert/update 쿼리를 실행
        // id 값이 있다면 update 가, 없으면 insert 쿼리가 수행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("abc@gmail.com")
                .build()
        );

        // 테이블 posts에 있는 모든 데이터를 조회하는 메소드
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
