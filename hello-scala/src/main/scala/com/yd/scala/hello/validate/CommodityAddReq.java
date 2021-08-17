package com.yd.scala.hello.validate;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author created by Zeb灬D on 2021-08-13 15:38
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommodityAddReq implements Serializable {
    private static final long serialVersionUID = -1L;

    @NotEmpty
    private String categoryId;

    @NotEmpty
    @Length(min = 1, max = 80)
    private String name;

    @NotEmpty
    @Length(min = 1, max = 85)
    private String desc;

    /**
     * 商品媒体素材
     */
    @NotEmpty
    private List<Object> medias;

    /**
     * 虚拟商品，1-是
     */
    private Integer virtual;
    /**
     * 试用，1-试用
     */
    private Integer onTrial;
    /**
     * 试用时间，单位秒，默认0
     */
    private Integer onTrialTime;

    /**
     * 售卖是否有现在，1-无库存限制
     */
    private Integer saleType;

    /**
     * 商品状态
     */
    private Integer commodityStatus;

    /**
     * sku信息
     */
    @NotEmpty
    private List<Object> skus;

    @NotEmpty
    private String programId;
}
