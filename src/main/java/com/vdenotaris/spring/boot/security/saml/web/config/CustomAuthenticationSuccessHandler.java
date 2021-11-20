package com.vdenotaris.spring.boot.security.saml.web.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.UUID;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest request,
                                        javax.servlet.http.HttpServletResponse response,
                                        Authentication authentication)
        throws IOException,
        javax.servlet.ServletException {

        // TODO get authenticated username and create a one time auth token
        String onetimeAuthToken = UUID.randomUUID().toString();
        User user = (User) authentication.getDetails();
        redirectStrategy.sendRedirect(request, response, "/landing?auth=" + onetimeAuthToken + "&username=" + user.getUsername());

    }
}
