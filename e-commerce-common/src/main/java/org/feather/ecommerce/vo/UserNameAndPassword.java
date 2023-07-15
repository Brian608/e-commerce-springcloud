package org.feather.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.vo
 * @className: UserNameAndPassword
 * @author: feather
 * @description: TODO
 * @since: 2023-07-14 8:43
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNameAndPassword {


    private String username;


    private String password;
}
