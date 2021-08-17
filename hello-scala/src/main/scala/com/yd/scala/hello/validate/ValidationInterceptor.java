package com.yd.scala.hello.validate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;


@Component
@Aspect
@EnableAspectJAutoProxy
public class ValidationInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationInterceptor.class);

    private final Validator validator;

    //这种就不用注入了
//    private static final Validator VALIDATOR = Validation.byProvider(HibernateValidator.class)
//            .configure()
//            .failFast(Boolean.FALSE)
//            .buildValidatorFactory()
//            .getValidator();

    public ValidationInterceptor(Validator validator) {
        this.validator = validator;
    }

    /**
     * 切面拦截的服务接口，包含以下条件：
     * 1.  包路径下，包含子包；
     * 3. 公共接口
     */
    @Pointcut("@annotation(EnableValidation)")
    public void ServiceMethod() {
    }

    /**
     * 执行前拦截，通过抛出校验异常来中断执行过程。
     * 只拦截添加 {@link EnableValidation} 注解的方法。
     *
     * @param point JoinPoint
     */
    @Before("ServiceMethod()")
    public void beforeServiceMethodInvoke(JoinPoint point) {
        final MethodSignature signature = (MethodSignature) point.getSignature();
        final Method method = signature.getMethod();
        final Parameter[] parameters = method.getParameters();
        if (parameters.length <= 0) {
            return;
        }
        final Object bean;
        try {
            bean = ValidationBeanWrapper.beanWrapper(point.getTarget().getClass(), method, point.getArgs());
        } catch (Exception e) {
            LOGGER.warn("tag: 接口参数校验, result：发生错误，method: {}", method, e);
            throw new IllegalArgumentException("CommonBizExceptionCode.UNKNOWN_ERROR");
        }
        LOGGER.info("tag: 接口参数校验, msg: 正在校验, bean：{}", bean);
        doValidation(bean);
    }

    public void doValidation(Object parameter) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(parameter);
        if (violations.isEmpty()) {
            return;
        }
        // 校验结果只取第一个错误提示
        final ConstraintViolation<Object> cve = violations.iterator().next();
        final String firstMessage = cve.getMessage();
        LOGGER.info("tag: 接口参数校验, result：未通过，parameter:{}, 1st.message:{}", parameter, firstMessage);
        if (isTemplate(firstMessage)) {
            throw new IllegalArgumentException("CommonBizExceptionCode.INPUT_INVALID" + firstMessage);
        } else {
            throw new IllegalArgumentException("CommonBizExceptionCode.INPUT_INVALID" + firstMessage + firstMessage);
        }
    }


    public static boolean isTemplate(String template) {
        if (template == null || template.isEmpty()) {
            return false;
        }
        final int size = template.length();
        return (size > 2 && '{' == template.charAt(0) && '}' == template.charAt(size - 1));
    }

}
