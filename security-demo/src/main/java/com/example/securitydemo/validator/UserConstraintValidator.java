package com.example.securitydemo.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @author jia
 */
@Slf4j
public class UserConstraintValidator implements ConstraintValidator<UserConstraint, Object> {

//    @Autowired
    // 可以注入任何服务

    @Override
    public void initialize(UserConstraint constraintAnnotation) {
        log.info("init");
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        log.info(o.toString());
        return false;
    }

}
