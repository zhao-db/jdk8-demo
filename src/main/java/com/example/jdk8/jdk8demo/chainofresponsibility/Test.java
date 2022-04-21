package com.example.jdk8.jdk8demo.chainofresponsibility;


/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/3
 * @since 3.0.1
 */
public class Test {

    public static void main(String[] args) {
        Request request = new Request();
        request.setRequest("sask被l 敬业d<mfa]k[ <被就业> s<就业> p[敏感] sadfjkl:): :(:");
        Response response = new Response();
        response.setResponse("a");

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HTMLFilter());
        filterChain.addFilter(new SensitiveFilter());
        filterChain.addFilter(new FaceFilter());
        filterChain.doFilter(request, response, filterChain);
        System.out.println(request.getRequest() + ":" + response.getResponse());
    }
}
