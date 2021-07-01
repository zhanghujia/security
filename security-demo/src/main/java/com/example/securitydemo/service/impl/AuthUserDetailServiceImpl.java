package com.example.securitydemo.service.impl;

import com.example.securitydemo.mapper.UsersMapper;
import com.example.securitydemo.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author jia
 */

@Slf4j
@Service
@Component("userDetailsService")
public class AuthUserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) {
        log.info("登录用户名: {}", s);
        Users users = usersMapper.queryByName(s.trim());
        if (Objects.isNull(users)) {
            throw new UsernameNotFoundException("用户不存在,请先注册");
        }
        String passWord = null;
        if (users.getPassWord() == null) {
            passWord = passwordEncoder.encode("sms");
        } else {
            passWord = users.getPassWord();
        }
        return buildUser(s, passWord);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) {
        log.info("登录第三方用户标识: {}", userId);
        Users users = usersMapper.selectByPrimaryKey(userId);
        if (Objects.isNull(users)) {
            throw new UsernameNotFoundException("用户不存在,请先注册");
        }
        String passWord = users.getPassWord();
        return (SocialUserDetails) buildUser(userId, passWord);
    }

    private UserDetails buildUser(String s, String passWord) {
        log.info("数据库密码: {}", passWord);
        return new SocialUser(s, passWord,
                true, true,
                true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
    }

}
