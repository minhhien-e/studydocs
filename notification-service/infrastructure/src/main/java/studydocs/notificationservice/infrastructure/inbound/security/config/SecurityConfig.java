package studydocs.notificationservice.infrastructure.inbound.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Set;

@Configuration
public class SecurityConfig {
    public static final Set<String> PUBLIC_URLS = Set.of("/swagger-ui/**", "/v3/api-docs/**", "/actuator/**");

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //authorize
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(PUBLIC_URLS.toArray(new String[0])).permitAll().anyRequest().authenticated());
        //csrf
        http.csrf(AbstractHttpConfigurer::disable);
        //cors
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
