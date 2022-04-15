package com.yd.scala.webflux.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author created by Zeb灬D on 2022-02-21 14:33
 */
@Service
public class IdCardService {
    //假设Object对应MySql一个表的身份证，很多地方都要维护Object，问save在高并发情况下会失效吗
    @Transactional //已经spring AOP了
    public void save(Object o) {
        //1、通过唯一索引如idCard查找出 o1

        //2、逻辑判断是否为空

        //3、do otherThing that spend time cost

        //4、如果为空则新增 insert，否则更新 update t set ... where idCard=o1.idCard
    }

    public void updateIdCardById(long id, String ic) {
        //直接更新数据库update t set idCard=ic where id=id
    }
}
