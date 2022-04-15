package com.yd.test;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;


public class CommodityMedia {

    @Test
    public void main() {
        System.out.println(CommodityMediaEnum.getByCode(1));
    }

    public static enum CommodityMediaEnum{
        PICTURE(1, "商品图片"),
        VIDEO(2, "商品视频"),
        PICTURE_DETAIL(3, "商品详情图片"),
        VIDEO_DETAIL(4, "商品详情视频"),
        OTHER(9, "其它"),
        ;

        @Setter
        @Getter
        private Integer code;    //函数编码

        @Setter
        @Getter
        private String message; //备注

        CommodityMediaEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public static CommodityMediaEnum getByCode(Integer code) {
            for (CommodityMediaEnum value : CommodityMediaEnum.values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }
}