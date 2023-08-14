package org.feather.ecommerce.service;

import org.feather.ecommerce.filter.AccessContext;
import org.feather.ecommerce.vo.LoginUserInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service
 * @className: BaseTest
 * @author: feather
 * @description:测试用例基类, 填充登录用户信息
 * @since: 2023-08-14 21:55
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseTest {

    protected final LoginUserInfo loginUserInfo = new LoginUserInfo(
            10L, "feather"
    );

    @Before
    public void init() {
        AccessContext.setLoginUserInfo(loginUserInfo);
    }

    @After
    public void destroy() {
        AccessContext.clearLoginUserInfo();
    }
}

