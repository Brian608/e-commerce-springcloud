package org.feather.ecommerce.feign;

import org.feather.ecommerce.account.AddressInfo;
import org.feather.ecommerce.common.TableId;
import org.feather.ecommerce.feign.hystrix.AddressClientHystrix;
import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.feign
 * @className: AddressClient
 * @author: feather
 * @description: 用户账户服务 Feign 接口(安全的)
 * @since: 2023-09-11 22:09
 * @version: 1.0
 */
@FeignClient(
        contextId = "AddressClient",
        value = "e-commerce-account-service",
        fallback = AddressClientHystrix.class
)
public interface AddressClient {
    /**
     * <h2>根据 id 查询地址信息</h2>
     * */
    @RequestMapping(
            value = "/ecommerce-account-service/address/address-info-by-table-id",
            method = RequestMethod.POST
    )
    CommonResponse<AddressInfo> getAddressInfoByTablesId(@RequestBody TableId tableId);
}
