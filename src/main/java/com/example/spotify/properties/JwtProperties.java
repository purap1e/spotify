package com.example.spotify.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("jwt")
@Component
@Getter
@Setter
public class JwtProperties {
    private String secret;
    private int expiresAt;
}
