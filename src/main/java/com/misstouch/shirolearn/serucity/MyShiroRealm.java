package com.misstouch.shirolearn.serucity;

import com.misstouch.shirolearn.entity.Role;
import com.misstouch.shirolearn.entity.User;
import com.misstouch.shirolearn.repository.UserRepository;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hechao
 * @date create in 21:12 2018/5/7/007
 */

public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    @Autowired
    UserRepository userRepository;
    /**
     * 权限认证 ----  为当前登录的subject授予角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("execute shiro authorization");
        //获取当前登录的用户名
//        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        User user = (User) principalCollection.getPrimaryPrincipal();
        if (user!=null) {
            //权限信息对象，用来存放查出的用户的角色和权限
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        }
        return null;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来用来存放登录提交的信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        logger.info("current subject's tocken is : "+ ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        User user = userRepository.findByUsername(token.getUsername());
        if (user!=null) {
//            subject.login(token);
//            doGetAuthorizationInfo((PrincipalCollection)user);
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(
                    user, //用户名
                    user.getPassword(), //密码
                    getName()  //realm name
            );
        }
        return null;
    }
}
