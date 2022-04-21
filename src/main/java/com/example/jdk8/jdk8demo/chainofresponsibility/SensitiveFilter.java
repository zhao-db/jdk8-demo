package com.example.jdk8.jdk8demo.chainofresponsibility;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/3
 * @since 3.0.1
 */
public class SensitiveFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        //处理字符串中的敏感信息，将被就业和谐成就业
        String requestMsg = request.getRequest()
                .replace("被就业", "就业").replace("敏感", "") +
                //后面添加的是便于我们观察代码执行步骤的字符串
                " ---sensitiveFilter()";
        request.setRequest(requestMsg);
        filterChain.doFilter(request, response, filterChain);
        String responseMsg = response.getResponse() + "---sensitiveFilter()";
        response.setResponse(responseMsg);
    }
}
