package com.example.securitycontextdemo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    public CustomAuthenticationFailureHandler failureHandler;
    @Autowired
    public CustomAuthenticationSuccessHandler successHandler;
    @Bean
    public InitializingBean initializingBean() {
        return () -> SecurityContextHolder
                .setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(f -> {
            f.successHandler(successHandler);
            f.failureHandler(failureHandler);
        });

    //        http.httpBasic(c -> {
//            c.realmName("LukeSkywalker");
//            c.authenticationEntryPoint(new CustomEntryPoint());
//        });
//        http.formLogin(f -> f.defaultSuccessUrl("/home", true));

//        http.formLogin().defaultSuccessUrl("/home", true);
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        var uds = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("mary")
                .password("12345")
                .authorities("read")
                .build();

        var user2 = User.withUsername("john")
                        .password("12345")
                                .authorities("write")
                                        .build();

        uds.createUser(user1);
        uds.createUser(user2);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      return NoOpPasswordEncoder.getInstance();
    }
}
