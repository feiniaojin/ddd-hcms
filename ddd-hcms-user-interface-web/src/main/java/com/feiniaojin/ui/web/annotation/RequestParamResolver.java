package com.feiniaojin.ui.web.annotation;

import com.feiniaojin.application.service.core.dto.BaseQuery;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 *@Author anzhenjiang
 *@Description TODO
 */
@Component
public class RequestParamResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasMethodAnnotation(RequestParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        assert nativeRequest != null;
        BaseQuery query = new BaseQuery(nativeRequest.getQueryString());
        webRequest.setAttribute("query",  query, RequestAttributes.SCOPE_REQUEST);
        return query;
    }
}
