package org.feather.ecommerce.converter;

import org.feather.ecommerce.constant.BrandCategoryEnum;

import javax.persistence.AttributeConverter;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.converter
 * @className: BrandCategoryEnumConverter
 * @author: feather
 * @description: 品牌分类枚举属性转换器
 * @since: 2023-08-16 22:33
 * @version: 1.0
 */

public class BrandCategoryEnumConverter implements AttributeConverter<BrandCategoryEnum, String> {

    @Override
    public String convertToDatabaseColumn(BrandCategoryEnum brandCategory) {
        return brandCategory.getCode();
    }

    @Override
    public BrandCategoryEnum convertToEntityAttribute(String code) {
        return BrandCategoryEnum.of(code);
    }
}
