package kr.ac.kopo.midtermproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // CSRF 보안 비활성화
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(Customizer.withDefaults());

        //FormLogin, BasicHttp 비활성화
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/notice/**").hasAnyRole("USER", "ADMIN", "MANAGER") // "/notice/"로 시작하는 모든 요청에 대해 USER, ADMIN, MANAGER 역할 허용
                .anyRequest().permitAll() // 그 외의 요청은 인증 없이 허용
        );

        return httpSecurity.build();
    }
}
