package com.yd.test;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 数据库对象，基本属性DO：有主键id、创建'修改时间
 *
 * @author created by Zeb灬D on 2020-04-13 20:54
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class BaseDO implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 数据库记录ID
     */
    @TableField("id")
    private Long id;

    /**
     * 环境 0:预发,1:其他
     */
    @TableField("env")
    private Integer env;


    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 修改人
     */
    @TableField("modified_by")
    private String modifiedBy;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Long gmtCreate;

    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private Long gmtModified;


}
