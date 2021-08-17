package com.yd.scala.hello;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * 获取泛型具体class
 *
 * @author created by Zeb灬D on 2021-07-15 13:53
 */
public class GenericSuperclassTest {

    public static void main(String[] args) {//System.out.println(AtomicLong.VM_SUPPORTS_LONG_CAS);
        //当前能获取到泛型
        BaseValidation<Long, String> validation = new CBaseValidation();
        System.out.println(validation.validation(1L, "1"));
        System.out.println(validation.clazzP);
        System.out.println(validation.clazzT);

        //泛型不能传递下去，即父子类都没有明确泛型
        BaseValidation<Long, String> validation1 = new GBaseValidation<Long, String>();
        System.out.println(validation1.validation(1L, "1"));
        System.out.println(validation1.clazzP);
        System.out.println(validation1.clazzT);

        //泛型不能传递下去，即父子类都没有明确泛型，这里也不行
        BaseValidation validation3 = new G2BaseValidation<String, String>();
        System.out.println(validation3.validation(1L, "1"));
        System.out.println(validation3.clazzP);
        System.out.println(validation3.clazzT);
    }

    static class G2BaseValidation<T, P> extends BaseValidation<T, P> {
        public G2BaseValidation() {
            Type type = this.getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) type;
                Type actualTypeArgumentT = pType.getActualTypeArguments()[0];
                if (actualTypeArgumentT instanceof Class) {
                    this.clazzT = (Class<T>) actualTypeArgumentT;
                }
                Type actualTypeArgumentP = pType.getActualTypeArguments()[1];
                if (actualTypeArgumentP instanceof Class) {
                    this.clazzP = (Class<P>) actualTypeArgumentP;
                }
            }
        }

        @Override
        boolean validation(T param, P key) {
            return Objects.equals(param, key);
        }
    }

    static class GBaseValidation<T, P> extends BaseValidation<T, P> {

        @Override
        boolean validation(T param, P key) {
            return Objects.equals(param, key);
        }
    }

    static class CBaseValidation extends BaseValidation<Long, String> {

        @Override
        public boolean validation(Long cappId, String openUid) {
            return StringUtils.equals(openUid, String.valueOf(cappId));
        }
    }

    static abstract class BaseValidation<T, Param> {
        protected Class<T> clazzT = null;
        protected Class<Param> clazzP = null;

        {
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) type;
                Type actualTypeArgumentT = pType.getActualTypeArguments()[0];
                if (actualTypeArgumentT instanceof Class) {
                    this.clazzT = (Class<T>) actualTypeArgumentT;
                }
                Type actualTypeArgumentP = pType.getActualTypeArguments()[1];
                if (actualTypeArgumentP instanceof Class) {
                    this.clazzP = (Class<Param>) actualTypeArgumentP;
                }
            }
        }

        /**
         * 校验权限
         *
         * @param param 参数
         * @param key   开发者uid
         * @return 成功与否
         */
        abstract boolean validation(T param, Param key);
    }
}
