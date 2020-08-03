package com.example.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity // 웹 보안 지원을 활성화하고 spring MVC통합 제공
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 보안해야 할 URL 경로와 그렇지 않은 URL 경우 정의
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll() // / 및 /home 경로는 인증이 필요하지 않음음
               .anyRequest().authenticated()
                .and()
                .formLogin()// 사용자가 성공적으로 로그인하면 인증이 필요한 이전에 요청한 페이지로 리디렉션
                .loginPage("/login") // 지정된 사용자 정의/ 로그인 페이지가 있으며 누구나 볼 수 있다.
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    // 단일 사용자로 인 메모리 사용자 저장소 설정
    // 해당 사용자에게는 사용자 이름, 암호 및 USER 역할 부여
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}