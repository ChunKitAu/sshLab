package com.ssh.interceptor;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.ssh.auth.domain.User;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * @auther ChunKitAu
 * @create 2020-05-17 17
 */
public class AuthInterceptor extends AbstractInterceptor  {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        ActionContext actionContext = actionInvocation.getInvocationContext();

        HttpServletRequest request =(HttpServletRequest)actionContext.get(StrutsStatics.HTTP_REQUEST);
        String auth = request.getHeader("auth");

        if(auth == null){
            return "authFail";
        }else {
            try{
                //解析token 得到操作用户的信息  并通过反射为action的值
                Object action = actionInvocation.getAction();
                Class<?> aClass = action.getClass();
                Field loginUser = aClass.getDeclaredField("loginUser");

                String s = Base64.decodeStr(auth, "utf-8");
                User user1 = JSONUtil.toBean(s, User.class);

                //设置操作用户信息
                loginUser.setAccessible(true);
                loginUser.set(action,user1);
            }catch (Exception  e){
                //发生异常 即解析错误 直接返回
                return "authFail";
            }
        }
        return actionInvocation.invoke();
    }


}
