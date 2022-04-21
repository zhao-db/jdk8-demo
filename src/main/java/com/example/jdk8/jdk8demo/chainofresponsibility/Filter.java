package com.example.jdk8.jdk8demo.chainofresponsibility;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/3
 * @since 3.0.1
 */
public interface Filter {
    /**
     * @param request
     * @param response
     * @param filterChain
     */
    void doFilter(Request request, Response response, FilterChain filterChain);


}
