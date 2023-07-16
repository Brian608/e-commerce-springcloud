package org.feather.ecommerce.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.util.TokenParseUtil;
import org.feather.ecommerce.vo.LoginUserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service
 * @className: JWTServiceTest
 * @author: feather
 * @description: TODO
 * @since: 2023-07-16 7:44
 * @version: 1.0
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTServiceTest {


    @Autowired
    private  IJWTService ijwtService;

    @Test
    public void testGenerateAndParseToken() throws Exception {
        String jwtToken = ijwtService.generateToken("feather", "25d55ad283aa400af464c76d713c07ad");
        log.info("jwt token is [{}]",jwtToken);

        LoginUserInfo loginUserInfo = TokenParseUtil.parseUserInfoFromToken(jwtToken);
        log.info("parse token :[{}]", JSON.toJSON(loginUserInfo));
    }


}
