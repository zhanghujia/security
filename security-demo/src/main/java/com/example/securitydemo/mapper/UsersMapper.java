package com.example.securitydemo.mapper;

import com.example.securitydemo.model.Users;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author jia
 */
@Repository
public interface UsersMapper extends Mapper<Users> {

    /**
     * 依据用户名查找用户
     *
     * @param userName 用户名
     * @return 实例对象数组
     */
    Users queryByName(String userName);

    /**
     * 新增用户
     *
     * @param users 实例对象
     * @return 主键
     */
    @Override
    int insert(Users users);


}
