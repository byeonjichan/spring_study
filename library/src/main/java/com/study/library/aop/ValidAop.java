package com.study.library.aop;

import com.study.library.dto.SignupReqDto;
import com.study.library.exception.ValidException;
import com.study.library.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class ValidAop {
    @Autowired
    private UserMapper userMapper;
    @Pointcut("@annotation(com.study.library.aop.annotation.ValidAspect)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName =proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;
        for (Object arg : args) {
            if (BeanPropertyBindingResult.class == arg.getClass()) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }
        }
        if (methodName.equals("signup")) {
            SignupReqDto signupReqDto = null;
            for (Object arg : args) {
                if (SignupReqDto.class == arg.getClass()) {
                    signupReqDto = (SignupReqDto) arg;
                }
            }
            if (userMapper.findUserByUsername(signupReqDto.getUsername()) != null) {
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자 이름입니다.");
                bindingResult.addError(objectError);
            }
        }
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errorMap.put(fieldName, message);
            }
            throw new ValidException(errorMap);
        }
        return proceedingJoinPoint.proceed();
    }
}

