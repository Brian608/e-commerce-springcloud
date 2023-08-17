package org.feather.ecommerce.converter;

import org.feather.ecommerce.constant.GoodsStatusEnum;

import javax.persistence.AttributeConverter;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.converter
 * @className: GoodsStatusConverter
 * @author: feather
 * @description:商品状态枚举属性转换器
 * @since: 2023-08-16 22:27
 * @version: 1.0
 */

public class GoodsStatusEnumConverter implements AttributeConverter<GoodsStatusEnum, Integer> {
    /**
     * <h2>转换成可以存入数据表的基本类型</h2>
     * */
    @Override
    public Integer convertToDatabaseColumn(GoodsStatusEnum goodsStatus) {
        return goodsStatus.getStatus();
    }

    /**
     * <h2>还原数据表中的字段值到 Java 数据类型</h2>
     * */
    @Override
    public GoodsStatusEnum convertToEntityAttribute(Integer status) {
        return GoodsStatusEnum.of(status);
    }
}
