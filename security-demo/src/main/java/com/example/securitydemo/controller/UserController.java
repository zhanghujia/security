package com.example.securitydemo.controller;

import com.example.securitydemo.model.Users;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jia
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/list")
    public List<Users> queryUserList() {
        List<Users> usersList = new ArrayList<>();
        Users users1 = new Users();
        Users users2 = new Users();
        Users users3 = new Users();
        usersList.add(users1);
        usersList.add(users2);
        usersList.add(users3);
        return usersList;
    }

    @GetMapping("/name")
    @ApiOperation(value = "依据用户名,查询用户列表")
    public List<Users> queryUserList(@ApiParam("用户名") @RequestParam("userName")
                                                 String userName) {
        List<Users> usersList = new ArrayList<>();
        Users users = new Users();
        users.setId(1L);
        users.setUserName("admin");
        log.info("userName: {}", userName);
        usersList.add(users);
        return usersList;
    }

    @GetMapping("/{id}")
    @JsonView(Users.UserDetailView.class)
    public Users queryUser(@PathVariable("id") Long id) {
        Users users = new Users();
        users.setId(1L);
        users.setUserName("admin");
        users.setPassWord("root");
        users.setAge(20);
        users.setBirthDay(Instant.now().toEpochMilli());
        log.info("id: {}", id);
        return users;
    }

    @GetMapping("/condition")
    public List<Users> queryUserList(Users users, @PageableDefault Pageable pageable) {
        List<Users> usersList = new ArrayList<>();
        Users users1 = new Users();
        users1.setId(1L);
        users1.setUserName("admin");
        log.info("users: {}", ReflectionToStringBuilder.toString(users, ToStringStyle.MULTI_LINE_STYLE));
        log.info("pageable:{}", pageable.toString());
        usersList.add(users1);
        return usersList;
    }

    @PostMapping("/insert")
    public Users createUser(@Valid @RequestBody Users users, BindingResult errors) {
        users.setId(1L);
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                String message = fieldError.getField() + ": " + fieldError.getDefaultMessage();
                log.info("error: {}", message);
            });
        }
        log.info("users: {}", users);
        return users;
    }

    @PutMapping("/update")
    public Users updateUser(@RequestBody Users users) {
        log.info("users: {}", users);
        return users;
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteUser(@PathVariable("id") Long id) {
        log.info("id: {}", id);
        return id != null;
    }

}
