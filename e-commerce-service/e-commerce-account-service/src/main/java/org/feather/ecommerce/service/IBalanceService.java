package org.feather.ecommerce.service;

import org.feather.ecommerce.account.BalanceInfo;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service
 * @className: IBalanceService
 * @author: feather
 * @description: 用于余额相关的服务接口定义
 * @since: 2023-08-14 21:00
 * @version: 1.0
 */
public interface IBalanceService {

    /**
     * <h2>获取当前用户余额信息</h2>
     * */
    BalanceInfo getCurrentUserBalanceInfo();

    /**
     * <h2>扣减用户余额</h2>
     * @param balanceInfo 代表想要扣减的余额
     * */
    BalanceInfo deductBalance(BalanceInfo balanceInfo);
}
