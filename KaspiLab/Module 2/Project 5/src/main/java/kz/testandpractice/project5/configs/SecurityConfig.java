package kz.testandpractice.project5.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain publicEndpoints(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/public/**")
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(AbstractHttpConfigurer::disable); // disable JWT for public endpoints
        return http.build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
        )
                .oauth2ResourceServer(oauth2 -> {
            oauth2.jwt(jwt ->
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()));
        });

        return httpSecurity.build();
    }

    @Bean
    public Converter<Jwt,? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        // Use the default converter for scopes/roles
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        log.debug("Granted Authorities Converter: {}", grantedAuthoritiesConverter);

        return jwt -> {
            // Get authorities from the default "scope" or "scp" claim
            Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

            // Add custom authorities based on another claim, e.g., "custom_roles"
            if (jwt.hasClaim("roles")) {
                log.debug("found roles claim" + jwt.getClaimAsStringList("roles"));
                Collection<GrantedAuthority> customAuthorities = jwt.getClaimAsStringList("roles").stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());
                authorities.addAll(customAuthorities);
            }

            // Return a new authentication token with combined authorities
            return new JwtAuthenticationToken(jwt, authorities);
        };
    }
}
