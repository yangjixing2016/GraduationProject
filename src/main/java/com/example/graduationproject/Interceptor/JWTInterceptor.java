package com.example.graduationproject.Interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.graduationproject.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果请求为 OPTIONS 请求，则返回 true,否则需要通过jwt验证
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        HashMap<String, Object> map = new HashMap<>();
        //获取请求头中的令牌
//        String token = request.getHeader("token");//所以前端需要将token放到请求头中
        String token = request.getHeader("Authorization");//所以前端需要将token放到请求头中
        System.out.println("请求头中的token:"+token);

        try {
            JWTUtils.verify(token);//验证令牌
            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg","无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","token算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效");
        }
        map.put("state",false);//设置状态
        //将map转为json 使用jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(json);
        return false;
    }
}
