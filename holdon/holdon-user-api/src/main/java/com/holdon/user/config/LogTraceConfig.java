package com.holdon.user.config;


import org.jboss.logging.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by wd on 2018/2/9.
 */
@Configuration
public class LogTraceConfig implements Filter {

    private String LOCAL_HOST_NAME = "UNKNOWN";

    public LogTraceConfig() {

        LOCAL_HOST_NAME = "localhost";
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String trackid;
        if (servletRequest instanceof HttpServletRequest) {
            trackid = ((HttpServletRequest) servletRequest).getHeader("trackid");
            if (StringUtils.isEmpty(trackid)) {
                trackid = UUID.randomUUID().toString();
            }
        } else {
            trackid = UUID.randomUUID().toString();
        }

        try {
            MDC.put("trackid", trackid);
            MDC.put("hostname", LOCAL_HOST_NAME);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {

    }
}
