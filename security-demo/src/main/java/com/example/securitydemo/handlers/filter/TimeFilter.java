package com.example.securitydemo.handlers.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.time.Instant;

/**
 * @author jia
 */
@Slf4j
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init Filter");
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        log.info("destroy Filter");
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        long startTime = Instant.now().toEpochMilli();
        log.info("doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        long endTime = Instant.now().toEpochMilli();
        log.info("耗时: {} ms", endTime - startTime);
    }

}
