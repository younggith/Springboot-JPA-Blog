package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
//		Member m = new Member(1, "ssar", "1234", "email");
		Member m = Member.builder().Username("ssar").password("1234").email("ssar@naver.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter : "+m.getUsername());
		return "lombok test 완료";
	}
	
	//인터넷 브라우저 요청은 Get요청 밖에 할 수 가 없다.
	// http://localhost:9090/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 "+m.getId()+", "+m.getUsername()+", "+m.getEmail();
	}
	
	// http://localhost:9090/http/post (insert)
	// text/plain -> postman raw형식
	// application/json
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // MessageConverter (스프링부트)
		return "post 요청 "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	// http://localhost:9090/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	// http://localhost:9090/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
