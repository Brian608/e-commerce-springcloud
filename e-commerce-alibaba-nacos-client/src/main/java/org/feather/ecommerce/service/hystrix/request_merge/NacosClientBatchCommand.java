package org.feather.ecommerce.service.hystrix.request_merge;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.service.NacosClientService;
import org.springframework.cloud.client.ServiceInstance;

import java.util.Collections;
import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.hystrix.request_merge
 * @className: NacosClientBatchCommand
 * @author: feather
 * @description:  批量请求command
 * @since: 2023-08-27 15:26
 * @version: 1.0
 */
@Slf4j
public class NacosClientBatchCommand  extends HystrixCommand<List<List<ServiceInstance>>> {

    private final NacosClientService nacosClientService;

    private final List<String> serviceIds;

    public NacosClientBatchCommand(NacosClientService nacosClientService, List<String> serviceIds) {
        super(
                HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("NacosClientBatchCommand")
        ));
        this.nacosClientService=nacosClientService;
        this.serviceIds = serviceIds;
    }

    @Override
    protected List<List<ServiceInstance>> run() throws Exception {
        log.info("use nacos client batch command to get result: [{}]",
                JSON.toJSONString(serviceIds));
        return nacosClientService.getNacosClientInfos(serviceIds);
    }

    @Override
    protected List<List<ServiceInstance>> getFallback() {
        log.warn("nacos client batch command failure, use fallback");
        return Collections.emptyList();
    }
}
