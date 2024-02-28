package com.example.demo.config;

import com.example.demo.service.UserService;
import com.example.demo.utils.JwtTokenUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        //request 中获取去 header
        String authHeader = request.getHeader(this.tokenHeader);
        //对header做判断
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            //取出header
            //此处注意token之前有一个7字符长度的“Bearer “，
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            //token中获取username
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            log.info("checking username:{}", username);
            //判断username
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //拿到userDetails，表示用户的核心信息
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //验证token
                if (jwtTokenUtil.validateToken(authToken,userDetails)) {
                    //完整填充的 authentication（其中包含了权限集 getAuthorities()）
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user:{}", username);
                    //建立安全上下文
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
