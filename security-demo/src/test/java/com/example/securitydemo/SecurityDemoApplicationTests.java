package com.example.securitydemo;

import com.example.securitycore.properties.SecurityProperties;
import com.example.securitydemo.mapper.UsersMapper;
import com.example.securitydemo.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityDemoApplicationTests {

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityProperties securityProperties;

    @Test
    void contextLoads() {
        Users users = new Users();
        users.setUserName("user");
        users.setBirthDay(System.currentTimeMillis());
        users.setAge(20);
        users.setPassWord(passwordEncoder.encode("123456"));
        usersMapper.insert(users);
        System.out.println(users);
    }

}
