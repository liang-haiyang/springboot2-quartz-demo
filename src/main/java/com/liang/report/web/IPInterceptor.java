package com.liang.report.web;

import com.liang.report.config.AppConfig;
import com.liang.report.utils.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author  lianghaiyang 2018/11/1 14:53
 */
@Component
public class IPInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceBusProtocolService.class);
    @Resource
    private AppConfig appConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //过滤ip,若用户在白名单内，则放行
        String ipAddress = IPUtils.getRealIP(request);
        LOGGER.info("USER IP ADDRESS IS =>" + ipAddress);
        if (StringUtils.isEmpty(ipAddress)) {
            return false;
        }
        List<String> allowOrigins = appConfig.getAllowOrigins();
        if (!allowOrigins.contains(ipAddress)) {
            response.getWriter().append("<h1 style=\"text-align:center;\">Not allowed!</h1>");
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}