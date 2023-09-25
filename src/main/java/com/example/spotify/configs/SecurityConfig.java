package com.example.spotify.configs;

import com.example.spotify.filters.CustomAuthenticationFilter;
import com.example.spotify.filters.CustomAuthorizationFilter;
import com.example.spotify.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProperties jwtProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests()
                        .antMatchers("/api/v1/auth/login").permitAll()
                        .antMatchers(
                                "/api/v1/users/save",
                                "/api/v1/users/add-role",
                                "/api/v1/users/add-role-to-user").hasAuthority("ADMIN")
                        .antMatchers("/api/v1/users").hasAuthority("USER")
                        .anyRequest().authenticated();
        http.addFilter(jwtAuthorizationFilter());
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), jwtProperties));
        http.addFilterBefore(new CustomAuthorizationFilter(jwtProperties), UsernamePasswordAuthenticationFilter.class);
    }

    public CustomAuthenticationFilter jwtAuthorizationFilter() throws Exception {
        CustomAuthenticationFilter jwtAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(), jwtProperties);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");
        return jwtAuthenticationFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
