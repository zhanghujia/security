package com.example.securitycore.social.config;

import com.example.securitycore.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author jia
 */

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository =
                new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator,
                        Encryptors.noOpText());
        // 可以修改表名前缀 repository.setTablePrefix("");
        // 数据库 连接 加密(当前未加)
        return repository;
    }

    @Bean
    public SpringSocialConfigurer springSocialConfigurer() {
        return new AutoSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessUrl());
    }

}
