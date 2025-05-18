package com.tsl.appointmenttracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void setCustomLogoutHandler(CustomLogoutHandler customLogoutHandler) {
        this.customLogoutHandler = customLogoutHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .permitAll())
                .logout(logout -> logout.addLogoutHandler(customLogoutHandler).
                        logoutUrl("/dologout"));
        return httpSecurity.build();
    }
}
