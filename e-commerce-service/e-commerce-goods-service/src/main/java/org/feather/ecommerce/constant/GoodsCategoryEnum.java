package org.feather.ecommerce.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.constant
 * @className: GoodsCategoryEnum
 * @author: feather
 * @description:
 * @since: 2023-08-16 22:34
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum GoodsCategoryEnum {
    DIAN_QI("10001", "电器"),
    JIA_JU("10002", "家具"),
    FU_SHI("10003", "服饰"),
    MY_YIN("10004", "母婴"),
    SHI_PIN("10005", "食品"),
    TU_SHU("10006", "图书"),
    ;

    /** 商品类别编码 */
    private final String code;

    /** 商品类别描述信息 */
    private final String description;

    /**
     * <h2>根据 code 获取到 GoodsCategory</h2>
     * */
    public static GoodsCategoryEnum of(String code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(code + " not exists")
                );
    }
}
