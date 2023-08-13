package org.feather.ecommerce.filter;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.filter
 * @className: LoginUserInfoInterceptor
 * @author: feather
 * @description:用户身份统一登录拦截
 * @since: 2023-08-13 10:30
 * @version: 1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.feather.ecommerce.constants.CommonConstants;
import org.feather.ecommerce.util.TokenParseUtil;
import org.feather.ecommerce.vo.LoginUserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("all")
@Slf4j
@Component
public class LoginUserInfoInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 部分请求不需要带有身份信息, 即白名单
        if (checkWhiteListUrl(request.getRequestURI())) {
            return true;
        }

        //先尝试从 http header 中拿到token
        String token = request.getHeader(CommonConstants.JWT_USER_INFO_KEY);
        LoginUserInfo loginUserInfo=null;
        try {
            loginUserInfo=   TokenParseUtil.parseUserInfoFromToken(token);
        }catch (Exception e){
            log.error("parse login user info error:[{}]",e.getMessage(),e);
        }
        //如果程序走到这里，说明header 中没有token 信息
        if (null==loginUserInfo){
            throw  new RuntimeException("can not parse current login user");
        }
        log.info("set login user info :[{}]",request.getRequestURI());
        //设置当前请求上下文 把用户信息填充进去
        AccessContext.setLoginUserInfo(loginUserInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 在请求完全结束之后调用，常用于清理资源等工作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
      if (null!=AccessContext.getLoginUserInfo()){
          AccessContext.clearLoginUserInfo();
      }
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * <h2>校验是否是白名单接口</h2>
     * swagger2 接口
     * */
    private boolean checkWhiteListUrl(String url) {

        return StringUtils.containsAny(
                url,
                "springfox", "swagger", "v2",
                "webjars", "doc.html"
        );
    }
}
