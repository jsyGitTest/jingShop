package com.mysiteforme.admin.base;

import com.mysiteforme.admin.config.ShiroConfig;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.realm.AuthRealm.ShiroUser;
import com.mysiteforme.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangl on 2017/11/30.
 * todo:
 */
@Component
public class MyHandlerInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MyHandlerInterceptor.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroConfig shiroConfig;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
//        LOGGER.info("当前请求路径.."+httpServletRequest.getRequestURI());
//        if (siteService == null || userService == null) {//解决service为null无法注入问题
//            System.out.println("siteService is null!!!");
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
//            siteService = (SiteService) factory.getBean("siteService");
            userService = (UserService) factory.getBean("userService");
//
//        }
//        httpServletRequest.setAttribute("site",siteService.getCurrentSite());
        ShiroUser user1 = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(null != user1){
            User user = userService.findUserById(user1.id);
            if(user != null){
                httpServletRequest.setAttribute("currentUser",user);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
