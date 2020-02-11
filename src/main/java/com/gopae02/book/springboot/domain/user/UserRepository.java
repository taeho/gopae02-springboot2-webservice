package com.gopae02.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);   // 소셜 로그인으로 반환되는 값 중 EMAIL을 통해 이미 생성된 사용자 판단
}