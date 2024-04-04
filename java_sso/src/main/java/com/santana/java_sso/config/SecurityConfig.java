package com.santana.java_sso.config;

import com.santana.java_sso.security.Oauth2AuthenticationSuccessHandler;
import com.santana.java_sso.security.RefererInStateOauth2AuthorizationRequestResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final static String[] WHITE_LIST_URLS = {
            "/oauth/**"
    };

    private final InMemoryClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfig(InMemoryClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors(Customizer.withDefaults()).authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URLS).permitAll().anyRequest().authenticated()).oauth2Login(oauth -> oauth.loginPage("/oauth/login").authorizationEndpoint(endpoint -> {
            endpoint.authorizationRequestResolver(oAuth2AuthorizationRequestResolver());
            endpoint.baseUri("/oauth2/authorization");
        }).successHandler(new Oauth2AuthenticationSuccessHandler()));

        return httpSecurity.build();
    }

    @Bean
    public OAuth2AuthorizationRequestResolver oAuth2AuthorizationRequestResolver() {
        DefaultOAuth2AuthorizationRequestResolver defaultOAuth2AuthorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, "/oauth2/authorization/**");

        return new RefererInStateOauth2AuthorizationRequestResolver(defaultOAuth2AuthorizationRequestResolver);
    }
}
