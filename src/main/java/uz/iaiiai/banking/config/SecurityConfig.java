package uz.iaiiai.banking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.iaiiai.banking.repository.UserRepository;
import uz.iaiiai.banking.security.CustomUserDetailsService;
import uz.iaiiai.banking.security.authentication.RestAuthenticationEntryPoint;
import uz.iaiiai.banking.security.authorization.RestAccessDeniedHandler;
import uz.iaiiai.banking.security.filter.JwtFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf(
                        csrf -> csrf.disable()
                )
                .cors(
                        httpSecurityCorsConfigurer ->
                                httpSecurityCorsConfigurer.disable()
                )
                .formLogin(
                        form -> form.disable()
                )
                .httpBasic(
                        basic -> basic.disable()
                )
                .sessionManagement(
                        session ->
                                session.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                )
                .authorizeHttpRequests(
                        authorize ->
                                authorize
                                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                                        .requestMatchers(
                                                "/v3/api-docs/**",
                                                "/swagger-ui.html",
                                                "/swagger-ui/**",
                                                "/ws/**",
                                                "/actuator/prometheus",
                                                "/actuator/**"
                                        ).permitAll()
                                        .requestMatchers(HttpMethod.GET, "/error").permitAll()
                                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                        .accessDeniedHandler(new RestAccessDeniedHandler())
                )
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
