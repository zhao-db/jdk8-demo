package com.example.jdk8.jdk8demo.chainofresponsibility;

//定义FaceFilter
public class FaceFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        //将字符串中出现的":):"转换成"^V^";
        String requestMsg = request.getRequest().replace(":):", "^V^")
                //后面添加的是便于我们观察代码执行步骤的字符串
                + "----FaceFilter()";
        chain.doFilter(request, response, chain);
        request.setRequest(requestMsg);
        String responseMsg = response.getResponse() + "---FaceFilter()";
        response.setResponse(responseMsg);
    }
}
