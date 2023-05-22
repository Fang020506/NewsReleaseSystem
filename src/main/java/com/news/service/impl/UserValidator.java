package com.news.service.impl;

import com.news.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
//用户验证校验器
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required", "id不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "用户名不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "密码不能为空");
    }
}