package com.example.SluzbaZaposljavanja.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(this.authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.headers().cacheControl().disable();
        httpSecurity.cors();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/oglasi/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/oglasi/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/oglasi/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/oglasi/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/gradjani/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/gradjani*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/konkursi/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/konkursi/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/oglasi*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/oglasi/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/firme/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/poslovi/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/konkursi").permitAll()
                .antMatchers(HttpMethod.POST, "/api/konkursi/**").permitAll()

                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

}
