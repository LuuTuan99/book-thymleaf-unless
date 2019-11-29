package com.fpt.config;

import com.fpt.entity.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailService();
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/members/register", "/*","/shop-item/*",
                        "/assets-client/**",
                        "/assets-admin/**","/cart/remove/*","/cart/buy/*","/cart/update","/cart/listCard").permitAll()
                    .antMatchers("/admin/**").hasAnyRole(Member.Role.ADMIN.getValue())
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/members/login")
                    .loginProcessingUrl("/members/login")
                    .defaultSuccessUrl("/",true)
                    .permitAll()
                    .failureUrl("/members/login?error")
                    .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/members/login")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/members/403");

    }
}
