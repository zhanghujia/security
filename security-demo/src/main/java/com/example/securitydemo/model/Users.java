package com.example.securitydemo.model;

import com.example.securitydemo.validator.UserConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author jia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView(Users.UserSimpleView.class)
public class Users implements Serializable {

    private static final long serialVersionUID = -9145299658853435597L;

    public interface UserSimpleView {
    }

    ;

    public interface UserDetailView extends UserSimpleView {
    }

    ;

    private Long id;
    @ApiModelProperty("用户名")
    @UserConstraint(message = "这是一个测试校验注解")
    private String userName;
    @NotBlank(message = "密码不可以为空")
    private String passWord;
    private Integer age;
    @JsonView(UserDetailView.class)
    private Long birthDay;

}
