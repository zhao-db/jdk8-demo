package com.example.jdk8.jdk8demo.chainofresponsibility;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/3
 * @since 3.0.1
 */
public class HTMLFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        //将字符串中出现的"<>"符号替换成"[]"
        String requestMsg = request.getRequest()
                .replace('<', '[').replace('>', ']') +
                //后面添加的是便于我们观察代码执行步骤的字符串
                "----HTMLFilter()";
        request.setRequest(requestMsg);
        filterChain.doFilter(request, response, filterChain);
        String responseMsg = response.getResponse() + "---HTMLFilter()";
        response.setResponse(responseMsg);

    }
}
