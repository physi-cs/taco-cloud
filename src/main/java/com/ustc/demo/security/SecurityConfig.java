package com.ustc.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //DataSource dataSource;

    @Resource(name = "userdetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

    }
        //auth.jdbcAuthentication()
        //.dataSource(dataSource)
        //.usersByUsernameQuery(
        //        "select username,password,enabled from Users where username =?")
        //.authoritiesByUsernameQuery(
        //        "select username, authority from UserAuthorities where username =?");
        //.passwordEncoder(new StandardPasswordEncoder("53cr3t"));

        //auth.inMemoryAuthentication()
        //        .passwordEncoder(new BCryptPasswordEncoder())
        //        .withUser("loulou")
        //        .password(new BCryptPasswordEncoder().encode("211314"))
        //        .authorities("ROLE_USER");


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders")
                    .access("hasRole('ROLE_USER')")
                    //.access("permitAll")
                .antMatchers("/", "/**")
                    .access("permitAll")
            .and()
                .formLogin()
                    .loginPage("/login")
            .and()
                .logout()
                    .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }
}
