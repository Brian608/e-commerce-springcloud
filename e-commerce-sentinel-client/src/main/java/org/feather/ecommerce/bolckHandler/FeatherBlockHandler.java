package org.feather.ecommerce.bolckHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.CommonResponse;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.bolckHandler
 * @className: FeatherBlockHandler
 * @author: feather
 * @description:
 * @since: 07-Oct-23 10:07 PM
 * @version: 1.0
 */
@Slf4j
public class FeatherBlockHandler {
    /**
     * 通用限流处理方法
     * 这个方法必须是 static
     * @param exception
     * @return
     */
    public static CommonResponse<String> featherHandleBlockException(BlockException exception){
        log.error("trigger feather block handler :[{}] ,[{}]",
                JSON.toJSONString(exception.getRule())
                ,exception.getRuleLimitApp());
        return  new CommonResponse<>(-1,"flow rule trigger block exception",null);

    }
}
