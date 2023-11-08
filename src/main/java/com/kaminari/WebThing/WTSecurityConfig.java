package com.kaminari.WebThing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WTSecurityConfig {
    @Autowired private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SHA256PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .disable()
                )
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/mypage").authenticated()
                        .requestMatchers("/mypage/**").authenticated()
                        .requestMatchers("/items").authenticated()
                        .requestMatchers("/items/**").authenticated()
                        .requestMatchers("/myitems").authenticated()
                        .requestMatchers("/myitems/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/mypage")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );
        return http.build();
    }

    public UserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager user = new JdbcUserDetailsManager(this.dataSource);
        return user;
    }

    private UserDetails makeUser(String username, String password, String role) {
        return User
                .withUsername(username)
                .password(passwordEncoder().encode(password))
                .roles(role)
                .disabled(false)
                .build();
    }
}
