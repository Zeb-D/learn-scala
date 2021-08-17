package com.yd.scala.hello.validate;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;

/**
 * @author created by Zeb灬D on 2021-07-15 17:23
 */
@Service
public class ValidateService {
    @EnableValidation
    public Boolean delete(
            @Min(value = 10000, message = "{capp.id.min}") Long cappId) {
        System.out.println("----cappId----" + cappId);
        return true;
    }

    @EnableValidation
    public Boolean delete(
            @Min(value = 10000, message = "{capp.id.min}") Long cappId,
            @Length(min = 1, max = 10, message = "{uid.range}") String uid) {
        System.out.println("----cappId----" + cappId + "-----uid-----" + uid);
        return true;
    }
}
