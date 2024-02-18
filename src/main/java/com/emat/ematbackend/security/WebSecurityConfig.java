//package com.emat.ematbackend.security;
//
//import com.emat.ematbackend.security.jwt.AuthEntryPointJwt;
//import com.emat.ematbackend.security.jwt.AuthTokenFilter;
//import com.emat.ematbackend.services.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//
//
//@Configuration
////@EnableWebSecurity
//@EnableMethodSecurity
////(securedEnabled = true,
////jsr250Enabled = true,
////prePostEnabled = true) // by default
//public class WebSecurityConfig {
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;
//
//    @Bean
//    public AuthTokenFilter authenticationJwtTokenFilter() {
//        return new AuthTokenFilter();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers("/api/auth/**").permitAll()
//                                .requestMatchers("/api/test/**").permitAll()
//                                .requestMatchers("/api/emat/**").permitAll()
//                                .requestMatchers("/api/emat/organisations").permitAll()
//                                .requestMatchers("/api/emat/saveOrganisations").permitAll()
//                                .requestMatchers("/api/emat/organisations/{id}").permitAll()
//
//                                .anyRequest().authenticated()
//                );
//        http.authenticationProvider(authenticationProvider());
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//
//       /* http
//                .cors(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize ->
//                        authorize
//                                .requestMatchers(HttpMethod.POST,"/api/auth/signin").permitAll()
//                                .requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/v1/**").permitAll()
//                                .requestMatchers(HttpMethod.DELETE, "/api/v1/**").permitAll()
//                                .requestMatchers(HttpMethod.PUT, "/api/v1/**").permitAll()
//                                .requestMatchers("/swagger-ui/**").permitAll()
//                                .requestMatchers("/v3/api-docs/**").permitAll()
//                                .anyRequest().permitAll()
//                ).exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(unauthorizedHandler)
//                ).sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();*/
//    }
//}