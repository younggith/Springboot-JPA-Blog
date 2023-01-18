package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;

// 자동으로 bean등록이 된다.
// @Repository 생략이 가능하다.
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
