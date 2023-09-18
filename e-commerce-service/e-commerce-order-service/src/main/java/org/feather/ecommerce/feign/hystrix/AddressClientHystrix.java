package org.feather.ecommerce.feign.hystrix;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.account.AddressInfo;
import org.feather.ecommerce.common.TableId;
import org.feather.ecommerce.feign.AddressClient;
import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.feign.hystrix
 * @className: AddressClientHystrix
 * @author: feather
 * @description: 账户服务熔断降级兜底策略
 * @since: 2023-09-11 22:12
 * @version: 1.0
 */

@Slf4j
@Component
public class AddressClientHystrix implements AddressClient {

    @Override
    public CommonResponse<AddressInfo> getAddressInfoByTablesId(TableId tableId) {

        log.error("[account client feign request error in order service] get address info" +
                "error: [{}]", JSON.toJSONString(tableId));
        return new CommonResponse<>(
                -1,
                "[account client feign request error in order service]",
                new AddressInfo(-1L, Collections.emptyList())
        );
    }
}

