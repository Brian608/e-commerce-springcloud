package org.feather.ecommerce.feign;

import org.feather.ecommerce.account.BalanceInfo;
import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.feign
 * @className: NotSecuredBalanceClient
 * @author: feather
 * @description: 用户账户服务 Feign 接口
 * @since: 2023-09-11 22:04
 * @version: 1.0
 */
@FeignClient(
        contextId = "NotSecuredBalanceClient",
        value = "e-commerce-account-service"
)
public interface NotSecuredBalanceClient {

    @RequestMapping(
            value = "/ecommerce-account-service/balance/deduct-balance",
            method = RequestMethod.PUT
    )
    CommonResponse<BalanceInfo> deductBalance(@RequestBody BalanceInfo balanceInfo);
}
