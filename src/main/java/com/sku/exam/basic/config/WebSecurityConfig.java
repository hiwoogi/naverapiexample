package com.sku.exam.basic.config;

import com.sku.exam.basic.security.JwtAuthenticationFilter;
import com.sku.exam.basic.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CorsFilter corsFilter;
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;  // Inject JwtAuthenticationFilter
    @Autowired
    private CorsConfig corsConfig;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                .authorizeRequests() // /와 /auth/** 경로는 인증 안해도 됨.
//                .antMatchers("/", "/auth/**").permitAll()
//                .anyRequest() // /와 /auth/**이외의 모든 경로는 인증 해야됨.
//                .authenticated();

        http.authorizeRequests(authorize-> {     // 권한 부여
            // authorizeRequests가 deprecated됨에 따라 authorizeHttpRequests 사용 권장
            authorize
//
                    .requestMatchers("/test3/**").permitAll()
                    .requestMatchers("/test2/**").permitAll()

                    .anyRequest().authenticated();  // 이외의 요청은 인증받아야함
        });
        // filter 등록.
        // 매 리퀘스트마다
        // CorsFilter 실행한 후에
        // jwtAuthenticationFilter 실행한다.
        http.addFilterBefore(new JwtAuthenticationFilter(tokenProvider), CorsFilter.class);

        return http.build();
    }

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter();
//    }
}