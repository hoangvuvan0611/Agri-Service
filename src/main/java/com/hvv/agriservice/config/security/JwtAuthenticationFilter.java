//package com.hvv.agriservice.config.security;
//
//import com.hvv.agriservice.utils.JwtUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter implements WebFilter {
//
//    private final JwtUtils jwtUtils;
////    private final ReactiveUserDetailsService userDetailsService;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
//            String jwt = authHeader.substring(7);
//            String username = jwtUtils.extractUsername(jwt);
//
//            if (StringUtils.hasText(username)) {
//
//            }
//        }
//        return null;
//    }
//}
