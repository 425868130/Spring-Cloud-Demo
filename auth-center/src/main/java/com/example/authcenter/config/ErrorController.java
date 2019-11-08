package com.example.authcenter.config;

import com.example.common.define.Result;
import com.example.common.define.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * 处理filter层异常,包装成统一返回对象
 */
@Slf4j
@RestController
public class ErrorController extends AbstractErrorController {

    public ErrorController() {
        super(new DefaultErrorAttributes());
    }

    @RequestMapping
    public Result error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> body = getErrorAttributes(request, false);
        String errorMsg = body.get("message").toString();
        return new Result(StatusCode.SYSTEM_ERROR, errorMsg);
    }

    @Override
    public String getErrorPath() {
        return "";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections
                .unmodifiableMap(getErrorAttributes(request, false));
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
    }
}
