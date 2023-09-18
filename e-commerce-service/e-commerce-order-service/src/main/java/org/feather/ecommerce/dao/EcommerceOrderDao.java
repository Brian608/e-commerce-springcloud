package org.feather.ecommerce.dao;

import org.feather.ecommerce.entity.EcommerceOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.dao
 * @className: EcommerceOrdeeDao
 * @author: feather
 * @description:
 * @since: 2023-09-10 22:34
 * @version: 1.0
 */

public interface EcommerceOrderDao  extends PagingAndSortingRepository<EcommerceOrder,Long> {

    /**
     * 根据userId 查询分页订单
     *   select * from t_ecommerce_order where user_id = ?
     *   order by ... desc/asc limit x offset y
     * @param userId
     * @param pageable
     * @return
     */
    Page<EcommerceOrder>  findAllByUserId(Long userId, Pageable pageable);
}
