package com.booksHub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {
    // LDAP or Database
    // In Memory Configuration
    // InMemoryUserDetailsManager(UserDetails...users)

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        Function<String,String> passwordEncoder
                = input ->passwordEncoder().encode(input);
           UserDetails userDetail1 = createNewUser( passwordEncoder,"sudip", "admin");
        UserDetails userDetail2 = createNewUser(passwordEncoder, "test", "test");


        return new InMemoryUserDetailsManager(userDetail1);
            }

    private static UserDetails createNewUser(Function<String, String> passwordEncoder, String username, String password) {
        return User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
// All Urls are protected
// A login form is shown for unauthorized requests
// CSFR disable
// Frames
// All this to connect h2
// We need to configure security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http.authorizeHttpRequests(
                auth ->auth.anyRequest().authenticated());

        http.formLogin(withDefaults());

        // Actual Thingg
        http.csrf().disable();
        http.headers().frameOptions().disable();


        return http.build();

    }


}








