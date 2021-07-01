package com.example.securitydemo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jia
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserConstraintValidator.class)
public @interface UserConstraint {

    String message() default "{javax.validation.constraints.UserConstraint.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
