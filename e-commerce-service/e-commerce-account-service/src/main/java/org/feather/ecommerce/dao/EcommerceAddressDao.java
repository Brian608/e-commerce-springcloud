package org.feather.ecommerce.dao;

import org.feather.ecommerce.entity.EcommerceAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.dao
 * @className: EcommerceAddressDao
 * @author: feather
 * @description:
 * @since: 2023-08-13 18:30
 * @version: 1.0
 */
public interface EcommerceAddressDao  extends JpaRepository<EcommerceAddress,Long> {

    /**
     * <h2>根据 用户 id 查询地址信息</h2>
     * */
    List<EcommerceAddress> findAllByUserId(Long userId);

}
