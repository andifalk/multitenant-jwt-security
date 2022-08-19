package com.example.server.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class DemoRestController {

    @GetMapping("/hello")
    Mono<String> hello(@AuthenticationPrincipal(errorOnInvalidType = true) Jwt jwt) {
        return Mono.just(String.format("it works for [%s] (%s)",
                jwt.getSubject(),
                jwt.getIssuer()));
    }

}
