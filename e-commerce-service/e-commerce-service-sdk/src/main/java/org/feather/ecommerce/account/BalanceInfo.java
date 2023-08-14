package org.feather.ecommerce.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.account
 * @className: BalanceInfo
 * @author: feather
 * @description:
 * @since: 2023-08-14 20:49
 * @version: 1.0
 */

@ApiModel(description = "用户账户余额信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceInfo {

    @ApiModelProperty(value = "用户主键 id")
    private Long userId;

    @ApiModelProperty(value = "用户账户余额")
    private Long balance;
}

