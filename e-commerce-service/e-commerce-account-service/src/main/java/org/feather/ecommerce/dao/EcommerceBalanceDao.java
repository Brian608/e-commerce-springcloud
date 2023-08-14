package org.feather.ecommerce.dao;

import org.feather.ecommerce.entity.EcommerceBalance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.dao
 * @className: EcommerceBalanceDao
 * @author: feather
 * @description:
 * @since: 2023-08-14 20:44
 * @version: 1.0
 */

public interface EcommerceBalanceDao extends JpaRepository<EcommerceBalance, Long> {

    /** 根据 userId 查询 EcommerceBalance 对象 */
    EcommerceBalance findByUserId(Long userId);
}

