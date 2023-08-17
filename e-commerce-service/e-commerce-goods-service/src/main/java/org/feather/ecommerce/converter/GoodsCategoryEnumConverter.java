package org.feather.ecommerce.converter;

import org.feather.ecommerce.constant.GoodsCategoryEnum;

import javax.persistence.AttributeConverter;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.converter
 * @className: GoodsCategoryEnumConverter
 * @author: feather
 * @description:商品类别枚举属性转换器
 * @since: 2023-08-16 22:35
 * @version: 1.0
 */

public class GoodsCategoryEnumConverter implements AttributeConverter<GoodsCategoryEnum, String> {

    @Override
    public String convertToDatabaseColumn(GoodsCategoryEnum goodsCategory) {
        return goodsCategory.getCode();
    }

    @Override
    public GoodsCategoryEnum convertToEntityAttribute(String code) {
        return GoodsCategoryEnum.of(code);
    }
}
