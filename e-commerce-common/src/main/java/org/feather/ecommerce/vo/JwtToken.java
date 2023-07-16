package org.feather.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.vo
 * @className: JwtToken
 * @author: feather
 * @description: 授权中心鉴权之后给客户端的token
 * @since: 2023-07-14 8:44
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {

    /**
     * jwt
     */
    private String  token;


}
