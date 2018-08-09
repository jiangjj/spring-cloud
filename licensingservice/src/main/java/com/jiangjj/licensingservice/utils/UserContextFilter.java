package com.jiangjj.licensingservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Component

public class UserContextFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.debug("I am entering the licensing service id with auth token: ", httpServletRequest.getHeader("Authorization"));
        UserContext.setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
//        UserContext.setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        filterChain.doFilter(httpServletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
