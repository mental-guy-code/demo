package com.example.demo.config;

import com.example.demo.common.CustomAuthenticationFilter;
import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 禁用CSRF保护，生产环境应该启用
                .cors() // 允许跨域请求
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll() // 允许所有人访问登录页面
                .anyRequest().authenticated() // 其他所有请求都需要认证
                .and()
//                .addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginProcessingUrl("/login") // 指定登录接口
                    .usernameParameter("username") // 前端提交用户名的参数名
                    .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandlerImpl()) // 登录成功处理
                    //.defaultSuccessUrl("/home", true) // 登录成功后跳转的URL
                .permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .and()
//                .addFilterBefore(new CustomAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);;
//                .and()
//                .logout().permitAll();
    }

    // 将UserDetailsService注入到AuthenticationManagerBuilder中。
    // 当进行认证时，Spring Security会调用UserDetailsService的loadUserByUsername方法来获取用户信息。
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        // 验证成功后跳转的URL
//        return new ForwardAuthenticationSuccessHandler("/login-success");
//    }



    // 密码的加密方式
    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
    // 此处暂时使用明文密码
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
