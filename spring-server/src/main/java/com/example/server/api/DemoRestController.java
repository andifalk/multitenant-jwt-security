package com.example.server.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoRestController {

    @GetMapping("/hello")
    String hello(@AuthenticationPrincipal(errorOnInvalidType = true) Jwt jwt) {
        return String.format("it works for [%s] (%s)",
                jwt.getSubject(),
                jwt.getIssuer());
    }

}
