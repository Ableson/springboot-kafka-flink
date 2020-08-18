package com.dtinone.datashare.common.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class LocalCorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setHeader("Access-Control-Allow-Origin", "*");//这里不能写*，*代表接受所有域名访问，如写*则下面一行代码无效。谨记
        resp.setHeader("Access-Control-Allow-Credentials", "true");//true代表允许携带cookie
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
