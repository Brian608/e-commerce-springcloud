package org.feather.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.vo
 * @className: LoginUserInfo
 * @author: feather
 * @description: 登录用户信息
 * @since: 2023-07-14 8:45
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfo {


    /**
     * 用户id
     */
    private Long id;


    /**
     * 用户名
     */
    private String username;


}
