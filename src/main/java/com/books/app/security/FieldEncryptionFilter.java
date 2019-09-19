package com.books.app.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class FieldEncryptionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request);
        System.out.println(response);
        System.out.println(chain);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
