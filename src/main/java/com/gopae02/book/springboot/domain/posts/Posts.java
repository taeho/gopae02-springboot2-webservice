package com.gopae02.book.springboot.domain.posts;

import com.gopae02.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타냄.
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙 나타냄. 스프링부트 2.0부터 IDENTITY써야 auto increment 가능.
    private Long id;

    @Column(length = 500, nullable = false)// 테이블의 칼럼을 나타낸다. 굳이 어노테이션 선언 안해도되긴함..
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // 해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}