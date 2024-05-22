package com.boostmytool.beststore.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
private CustomUserDetailsService  customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests
                .requestMatchers("/register","/","/login","/Home","/images/**","/css/**","/js/**")
                .permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(form  -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/products" , true)
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                                .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()

                );
        return http.build();



    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

   public void configure(AuthenticationManagerBuilder builder) throws Exception{
    builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
   }
}
