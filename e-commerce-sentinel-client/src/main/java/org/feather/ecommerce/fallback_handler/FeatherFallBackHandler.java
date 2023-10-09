package org.feather.ecommerce.fallback_handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.JwtToken;
import org.feather.ecommerce.vo.UserNameAndPassword;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.fallback_handler
 * @className: FeatherFallBackHandler
 * @author: feather
 * @description: sentinel 回退降级的兜底策略
 * 都需要静态的方法
 * @since: 09-Oct-23 8:56 PM
 * @version: 1.0
 */
@Slf4j
public class FeatherFallBackHandler {


    /**
     *  getTokenFromAuthorityService  方法的fallback
     * @param userNameAndPassword
     * @return
     */
    public  static JwtToken getTokenFromAuthorityServiceFallback(
            UserNameAndPassword userNameAndPassword
    ){
        log.error("get token from authority service fallback :[{}]",
                JSON.toJSONString(userNameAndPassword));
        return new JwtToken("feather- token");
    }

    /**
     * ignoreException 方法的 fallback
     * @param code
     * @return
     */
    public static JwtToken ignoreExceptionFallback(Integer code) {
        log.error("ignore exception input code: [{}] has trigger exception", code);
        return new JwtToken("feather-fallback");
    }
}
