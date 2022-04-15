package com.yd.test;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品表
 *
 * @author created by Zeb灬D on 2021-08-10 16:25
 */
@Setter
@Getter
@ToString(callSuper = true)
@TableName("mall_commodity")
public class MallCommodity extends BaseDO{
    /**
     * 商品code
     */
    @TableField("commodity_code")
    private String commodityCode;

    /**
     * 商品名称
     */
    @TableField("commodity_name")
    private String commodityName;

    /**
     * 商品类目Id
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 商品描述
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 是否虚拟商品，1-虚拟
     */
    @TableField("`virtual`")
    private Integer virtual;

    /**
     * 售卖是否有限制，1-无库存限制
     */
    @TableField("sale_type")
    private Integer saleType;

    /**
     * 是否试用，1-试用
     */
    @TableField("on_trial")
    private Integer onTrial;

    /**
     * 试用时间，单位s
     */
    @TableField("on_trial_day")
    private Integer onTrialTime;

    /**
     * 节目id
     */
    @TableField("program_id")
    private Long programId;

    /**
     * 商品状态：1-草稿，3-待审核，5-审核通过，7-上架状态，9-下架
     */
    @TableField("commodity_status")
    private Integer commodityStatus;

    /**
     * 官方认证状态，2-官方认证，0-默认未认证
     */
    @TableField("official_status")
    private Integer officialStatus;

    /**
     * 租户
     */
    @TableField("namespace")
    private String namespace;

    /**
     * 站点
     */
    @TableField("site")
    private String site;

}
