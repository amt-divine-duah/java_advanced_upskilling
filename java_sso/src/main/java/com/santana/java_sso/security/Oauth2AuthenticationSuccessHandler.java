package com.santana.java_sso.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Oauth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public static final String DEFAULT = "/";

    public static final String SEPARATOR = ",";

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance().query(request.getQueryString()).build();

        MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();
        String encodedState = queryParams.getFirst("state");
        if (encodedState == null) {
            return DEFAULT;
        }

        String decodedState = URLDecoder.decode(encodedState, StandardCharsets.UTF_8);
        String[] splittedState = decodedState.split(SEPARATOR);

        if (splittedState.length != 2) {
            return DEFAULT;
        } else if (Objects.equals(splittedState[1], "null")) {
            return DEFAULT;
        } else {
            return splittedState[1];
        }
    }
}
