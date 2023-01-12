package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// 자동으로 bean등록이 된다.
// @Repository 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{
	// JPA Naming 전략
	// SELECT * FROM user WHERE userName = ? AND password = ?;
	User findByUserNameAndPassword(String userName, String password);
	
	//위와 동일한 메서드
//	@Query(value = "SELECT * FROM user WHERE userName = ? AND password = ?", nativeQuery = true)
//	User login(String userName, String password);
}
