package com.santana.java_sso.security;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.santana.java_sso.security.Oauth2AuthenticationSuccessHandler.SEPARATOR;

public class RefererInStateOauth2AuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final DefaultOAuth2AuthorizationRequestResolver delegate;

    public RefererInStateOauth2AuthorizationRequestResolver(DefaultOAuth2AuthorizationRequestResolver delegate) {
        this.delegate = delegate;
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        OAuth2AuthorizationRequest oAuth2AuthorizationRequest = delegate.resolve(request);
        return patchState(oAuth2AuthorizationRequest);
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        OAuth2AuthorizationRequest oAuth2AuthorizationRequest = delegate.resolve(request, clientRegistrationId);
        return patchState(oAuth2AuthorizationRequest);
    }

    private OAuth2AuthorizationRequest patchState(OAuth2AuthorizationRequest auth2AuthorizationRequest) {
        if (auth2AuthorizationRequest == null) {
            return null;
        }
        return OAuth2AuthorizationRequest.from(auth2AuthorizationRequest).state(auth2AuthorizationRequest.getState()+getSeparatorRefererOrEmpty()).build();
    }

    private String getSeparatorRefererOrEmpty() {
        HttpServletRequest currentHttpRequest = getCurrentHttpRequest();

        if (currentHttpRequest!=null){
            String referer = currentHttpRequest.getHeader("Referer");
            String postLoginRedirectUri = currentHttpRequest.getParameter("post_login_redirect_uri");

            if (!StringUtils.isEmpty(postLoginRedirectUri) || !"null".equals(postLoginRedirectUri)) {
                return SEPARATOR+postLoginRedirectUri;
            } else if (!StringUtils.isEmpty(referer) || !"null".equals(referer)) {
                return SEPARATOR+referer;
            }
        }
        return "";
    }

    public static HttpServletRequest getCurrentHttpRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes)requestAttributes).getRequest();
        }
        return null;
    }
}
