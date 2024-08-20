package com.feiniaojin.ui.web.annotation;

import com.feiniaojin.application.service.content.entry.dto.EntryQuery;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Component
public class UrlRequestParamResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 判断参数是否被 @MyRequestParam 注解标记
        return parameter.hasParameterAnnotation(UrlRequestParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 获取注解中的参数名
        UrlRequestParam urlRequestParam = parameter.getParameterAnnotation(UrlRequestParam.class);
        String paramName = urlRequestParam.value();

        // 获取请求路径中的变量（Spring 已经将路径变量解析为一个 Map）
        Map<String, String> pathVariables = (Map<String, String>) webRequest.getAttribute(
                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);

        // 获取路径变量值
        String paramValue = pathVariables != null ? pathVariables.get(paramName) : null;

        // 根据参数类型进行转换（这里默认是 String 类型，复杂类型可以扩展）
        Class<?> paramType = parameter.getParameterType();
        if (paramType == String.class) {
            return paramValue;
        } else if (paramType == Integer.class) {
            return paramValue != null ? Integer.parseInt(paramValue) : null;
        } else if (paramType == EntryQuery.class) {
            return EntryQuery.parse(paramValue);
        }
        // 更多类型转换可以在这里添加

        return null;
    }
}
