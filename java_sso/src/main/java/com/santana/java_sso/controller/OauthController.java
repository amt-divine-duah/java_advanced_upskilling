package com.santana.java_sso.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class OauthController {

    private final ClientRegistrationRepository clientRegistrationRepository;
    public OauthController(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping("")
    public String index(Authentication user) {
        return "Hello user " + user;
    }

    @GetMapping("oauth/login")
    public RedirectView oauthLogin(HttpServletRequest request, @RequestParam(required = false) String postLoginRedirectUri) {

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("azure-dev");

        String baseUri = ServletUriComponentsBuilder.fromContextPath(request).build().toUriString();

        String authorizationUri = baseUri + OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/" + clientRegistration.getRegistrationId();

        return new RedirectView(authorizationUri + "?post_login_redirect_uri=" + postLoginRedirectUri);
    }
}
