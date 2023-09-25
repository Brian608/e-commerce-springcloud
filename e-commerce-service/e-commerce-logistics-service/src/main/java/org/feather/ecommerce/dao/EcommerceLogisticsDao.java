package org.feather.ecommerce.dao;

import org.feather.ecommerce.entity.EcommerceLogistics;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.dao
 * @className: EcommerceLogisticsDao
 * @author: feather
 * @description:
 * @since: 25-Sep-23 10:24 PM
 * @version: 1.0
 */
public interface EcommerceLogisticsDao extends JpaRepository<EcommerceLogistics, Long> {
}
