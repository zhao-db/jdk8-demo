package com.example.jdk8.jdk8demo.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/3
 * @since 3.0.1
 */
public class FilterChain implements Filter {

    List<Filter> filterList = new ArrayList<>();

    int index = 0;

    //往规则链条中添加规则
    public FilterChain addFilter(Filter f) {
        filterList.add(f);
        //代码的设计技巧:Chain链添加过滤规则结束后返回添加后的Chain，方便我们下面doFilter函数的操作
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (index == filterList.size()) {
            return;
        }
        Filter filter = filterList.get(index);
        index++;
        filter.doFilter(request, response, filterChain);
    }
}
