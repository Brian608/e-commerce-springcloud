package org.feather.ecommerce.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.account.BalanceInfo;
import org.feather.ecommerce.dao.EcommerceBalanceDao;
import org.feather.ecommerce.entity.EcommerceBalance;
import org.feather.ecommerce.filter.AccessContext;
import org.feather.ecommerce.service.IBalanceService;
import org.feather.ecommerce.vo.LoginUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.impl
 * @className: BalanceServiceImpl
 * @author: feather
 * @description: 用于余额相关服务接口实现
 * @since: 2023-08-15 7:22
 * @version: 1.0
 */

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BalanceServiceImpl implements IBalanceService {

    private final EcommerceBalanceDao balanceDao;

    public BalanceServiceImpl(EcommerceBalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    @Override
    public BalanceInfo getCurrentUserBalanceInfo() {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        BalanceInfo balanceInfo = new BalanceInfo(
                loginUserInfo.getId(), 0L
        );

        EcommerceBalance ecommerceBalance =
                balanceDao.findByUserId(loginUserInfo.getId());
        if (null != ecommerceBalance) {
            balanceInfo.setBalance(ecommerceBalance.getBalance());
        } else {
            // 如果还没有用户余额记录, 这里创建出来，余额设定为0即可
            EcommerceBalance newBalance = new EcommerceBalance();
            newBalance.setUserId(loginUserInfo.getId());
            newBalance.setBalance(0L);
            log.info("init user balance record: [{}]",
                    balanceDao.save(newBalance).getId());
        }

        return balanceInfo;
    }

    @Override
    public BalanceInfo deductBalance(BalanceInfo balanceInfo) {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 扣减用户余额的一个基本原则: 扣减额 <= 当前用户余额
        EcommerceBalance ecommerceBalance =
                balanceDao.findByUserId(loginUserInfo.getId());
        if (null == ecommerceBalance
                || ecommerceBalance.getBalance() - balanceInfo.getBalance() < 0
        ) {
            throw new RuntimeException("user balance is not enough!");
        }

        Long sourceBalance = ecommerceBalance.getBalance();
        ecommerceBalance.setBalance(ecommerceBalance.getBalance() - balanceInfo.getBalance());
        log.info("deduct balance: [{}], [{}], [{}]",
                balanceDao.save(ecommerceBalance).getId(), sourceBalance,
                balanceInfo.getBalance());

        return new BalanceInfo(
                ecommerceBalance.getUserId(),
                ecommerceBalance.getBalance()
        );
    }
}
