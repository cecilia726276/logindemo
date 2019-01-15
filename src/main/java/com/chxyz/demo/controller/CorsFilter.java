package com.chxyz.demo.controller;


import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "corsFilter", urlPatterns = "/*",
        initParams = {@WebInitParam(name = "allowOrigin", value = "*"),
                @WebInitParam(name = "allowMethods", value = "GET,POST,PUT,DELETE,OPTIONS"),
                @WebInitParam(name = "allowCredentials", value = "true"),
                @WebInitParam(name = "allowHeaders", value = "Content-Type,X-Token")})

public class CorsFilter implements Filter {
    //private final static String ALLOWORIGIN_CORS_A = "http://127.0.0.1:3000";
    private String allowOrigin = "http://127.0.0.1:3000";
    private String allowMethods = "GET, POST, PUT, DELETE";
    private String allowCredentials;
    private String allowHeaders = "Content-Type";
    private String exposeHeaders;


    public void init (FilterConfig filterConfig) throws ServletException{
        /*allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");*/
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Access-Control-Allow-Origin: 指定授权访问的域


        //response.addHeader("Access-Control-Max-Age", "1800");//30 min
        //filterChain.doFilter(request, response);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!StringUtils.isEmpty(allowOrigin)) {
            if(allowOrigin.equals("*")){
                response.setHeader("Access-Control-Allow-Origin", allowOrigin);
            }else{
                List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
                if (allowOriginList != null && allowOriginList.size() > 0) {
                    String currentOrigin = request.getHeader("Origin");
                    if (allowOriginList.contains(currentOrigin)) {
                        response.setHeader("Access-Control-Allow-Origin", currentOrigin);
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(allowMethods)) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (!StringUtils.isEmpty(allowCredentials)) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (!StringUtils.isEmpty(allowHeaders)) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (!StringUtils.isEmpty(exposeHeaders)) {
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }


}
