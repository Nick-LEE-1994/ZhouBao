package com.likey.zhoubao.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.likey.zhoubao.common.models.CurrentUser;
import com.likey.zhoubao.util.CurrentUserHolder;
import com.likey.zhoubao.util.RedisUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 用jwt的拦截器，而不是shiro拦截
 * 代码的执行流程 preHandle -> isAccessAllowed -> isLoginAttempt -> executeLogin
 * @author: likeyu
 * @create: 2020-11-16 16:33
 **/
public class JWTFilter extends BasicHttpAuthenticationFilter {

    RedisUtil redisUtil;

    /**
     * 对跨域提供支持(不太明白,照着写的）
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        //filter初始化时，bean还没有被注入，所以需要手动注入
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        redisUtil = (RedisUtil) webApplicationContext.getBean("redisUtil");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态(不明白)
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }

        //如果请求中有token，则取出当前用户信息置于线程对象，并重置token过期时间
        String token = ((HttpServletRequest) request).getHeader("Token");
        if (token != null) {
            String userInfo = redisUtil.getUserToken(token);
            JSONObject json = JSONObject.parseObject(userInfo);
            CurrentUser currentUser = JSON.toJavaObject(json, CurrentUser.class);
            CurrentUserHolder.setUser(currentUser);
            redisUtil.expireToken(token);
        }

        return super.preHandle(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断请求的请求头是否带上 "Token"
        if (((HttpServletRequest) request).getHeader("Token") != null) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                //token 错误
                try {
                    responseError(response);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Token字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Token");
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Token");

        //test token过期
        System.out.println("过期:" + redisUtil.getExpire(authorization));

        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 将非法请求跳转到 /unauthorized
     */
    private void responseError(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect("/unauthorized");
    }
}
