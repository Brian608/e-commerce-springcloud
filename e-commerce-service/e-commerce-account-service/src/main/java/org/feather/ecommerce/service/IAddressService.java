package org.feather.ecommerce.service;

import org.feather.ecommerce.account.AddressInfo;
import org.feather.ecommerce.common.TableId;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service
 * @className: IAddressService
 * @author: feather
 * @description:
 * @since: 2023-08-14 20:53
 * @version: 1.0
 */

public interface IAddressService {

    /**
     * <h2>创建用户地址信息</h2>
     * */
    TableId createAddressInfo(AddressInfo addressInfo);

    /**
     * <h2>获取当前登录的用户地址信息</h2>
     * */
    AddressInfo getCurrentAddressInfo();

    /**
     * <h2>通过 id 获取用户地址信息, id 是 EcommerceAddress 表的主键</h2>
     * */
    AddressInfo getAddressInfoById(Long id);

    /**
     * <h2>通过 TableId 获取用户地址信息</h2>
     * */
    AddressInfo getAddressInfoByTableId(TableId tableId);

}
