package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;

// 빈등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration
@EnableWebSecurity	// 시큐리티 필터 등록 => 스프링 시큐리티가 컨트롤러로 가기전에 모든 request요청을 가로챔
@EnableMethodSecurity(prePostEnabled = true)	// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws
		Exception {
		http
			.csrf().disable()	// csrf 토큰 비활성화 (테스트시엔 걸어두는게 좋음)
			.authorizeHttpRequests()
			.requestMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**").permitAll()	// /auth/이하의 경로로들어오는건 누구나 접근 가능
			.anyRequest().authenticated()	// 위경로가 아닌 다른 경로의 요청은 인증이 되야한다.
			.and()
			.formLogin()
			.loginPage("/auth/loginForm")	// 인증이 필요한곳으로 요청이온다면 이쪽으로 요청
			.loginProcessingUrl("/auth/loginProc")	//스프링 시큐리티가 해당주소로 요청오는 로그인을 가로챈다. 가로채서 대신 로그인 해준다.
			.defaultSuccessUrl("/");		// 정상요청시 이 url로 이동
		return http.build();
	}
}
