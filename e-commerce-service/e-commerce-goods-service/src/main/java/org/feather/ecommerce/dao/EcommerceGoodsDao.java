package org.feather.ecommerce.dao;

import org.feather.ecommerce.constant.BrandCategoryEnum;
import org.feather.ecommerce.constant.GoodsCategoryEnum;
import org.feather.ecommerce.entity.EcommerceGoods;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.dao
 * @className: EcommerceGoodsDao
 * @author: feather
 * @description:
 * @since: 2023-08-16 22:51
 * @version: 1.0
 */

public interface EcommerceGoodsDao extends PagingAndSortingRepository<EcommerceGoods, Long> {

    /**
     * <h2>根据查询条件查询商品表, 并限制返回结果</h2>
     * select * from t_ecommerce_goods where goods_category = ? and brand_category = ?
     * and goods_name = ? limit 1;
     * */
    Optional<EcommerceGoods> findFirst1ByGoodsCategoryAndBrandCategoryAndGoodsName(
            GoodsCategoryEnum goodsCategory, BrandCategoryEnum brandCategory,
            String goodsName
    );
}