package vn.fpt.diamond_shop.config;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;
import vn.fpt.diamond_shop.security.JwtAuthenticationEntryPoint;
import vn.fpt.diamond_shop.security.JwtAuthenticationFilter;
import vn.fpt.diamond_shop.security.JwtTokenProvider;
import vn.fpt.diamond_shop.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
            .and()
            .authorizeRequests()
            .antMatchers("/auth/register",
                        "/auth/login",
                        "/auth/validate-token",
                        "/product/get-jewelry",
                        "/product/get-jewelry-list",
                        "/product/get-diamond",
                        "/product/get-all-jewelry-types",
                        "/product/get-all-collections",
                        "/product/get-collection")
                .permitAll()
            .anyRequest()
                .authenticated()
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(tokenProvider()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtTokenProvider tokenProvider() {
        return new JwtTokenProvider(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}