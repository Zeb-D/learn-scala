package com.yd.dbAccess;

import lombok.Data;

/**
 * 数据库表信息
 * <p>
 * MySQL数据库系统表 from information.colums where table_name =''
 * Oracle DB: FROM user_tab_cols WHERE table_name=
 * </p>
 *
 * @author Yd on  2017-11-29
 * @Description：
 **/
@Data
public class TableInfo {
    private String columnName;//字段名
    private String dataType;//数据类型
    private String characterMaxLength;//字段长度
    private String columnNameDefault;//默认字段名
    private String nullable;//是否为空
    private Integer ordinalPosition;//字段对应下标

}
