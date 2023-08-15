package org.feather.ecommerce.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.account.BalanceInfo;
import org.feather.ecommerce.service.IBalanceService;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.controller
 * @className: BalanceController
 * @author: feather
 * @description:
 * @since: 2023-08-15 8:44
 * @version: 1.0
 */

@Api(tags = "用户余额服务")
@Slf4j
@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final IBalanceService balanceService;

    public BalanceController(IBalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @ApiOperation(value = "当前用户", notes = "获取当前用户余额信息", httpMethod = "GET")
    @GetMapping("/current-balance")
    public BalanceInfo getCurrentUserBalanceInfo() {
        return balanceService.getCurrentUserBalanceInfo();
    }

    @ApiOperation(value = "扣减", notes = "扣减用于余额", httpMethod = "PUT")
    @PutMapping("/deduct-balance")
    public BalanceInfo deductBalance(@RequestBody BalanceInfo balanceInfo) {
        return balanceService.deductBalance(balanceInfo);
    }
}
