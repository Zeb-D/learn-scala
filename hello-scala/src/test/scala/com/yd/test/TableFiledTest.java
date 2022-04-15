package com.yd.test;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yd.json.JSONObject;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author created by Zeb灬D on 2021-10-12 16:39
 */
public class TableFiledTest {

    @Test
    public void test() {
        System.out.println(JSONObject.toJSONString(getTableFields(MallCommodity.class)));
    }

    protected List<String> getTableFields(Class clazz) {
        List<String> superFields = Stream.of(clazz.getSuperclass().getDeclaredFields()).map(field -> field.getAnnotation(TableField.class)).filter(Objects::nonNull).map(t -> t.value()).collect(Collectors.toList());
        List<String> fields = Stream.of(clazz.getDeclaredFields()).map(field -> field.getAnnotation(TableField.class)).filter(Objects::nonNull).map(t -> t.value()).collect(Collectors.toList());
        superFields.addAll(fields);
        return superFields;
    }
}
