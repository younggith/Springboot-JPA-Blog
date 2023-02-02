package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder	//  빌더 패턴!!
//ORM -> Java(다른언어포함) Object -> 테이블로 매핑해주는 기술
@Entity	// User 클래스가 MySQL에 테이블이 생성이 된다.
// @DynamicInsert // null인필드를 제외하고 테이블에 insert
public class User {
	
	@Id	// Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;	// 시퀀스, auto increment
	
	@Column(nullable = false, length = 100, unique = true) // null이 될수없고 최대길이가 30자
	private String username; // id
	
	@Column(nullable = false, length = 100) // 123456 => 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	//DB는 RoleType이라는게 없다
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. // ADMIN, USER, MANAGER
	
	private String oauth;	// kakao, google, naver...
	
	@CreationTimestamp // 시간이 자동으로 입력됨
	private Timestamp createDate;
}
