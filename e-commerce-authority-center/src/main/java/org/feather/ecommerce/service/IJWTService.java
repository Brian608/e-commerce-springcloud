package org.feather.ecommerce.service;

import org.feather.ecommerce.vo.UserNameAndPassword;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service
 * @className: IJWTService
 * @author: feather
 * @description: jwt 相关服务接口定义
 * @since: 2023-07-15 18:36
 * @version: 1.0
 */

public interface IJWTService {

    /**
     * 生成jwt token  使用默认的超时时间
     * @param username
     * @param password
     * @return
     */
    String generateToken(String username,String password) throws  Exception;

    /**
     * 生成指定超时时间的token 单位是天
     * @param username
     * @param password
     * @param expire
     * @return
     * @throws Exception
     */
    String  generateToken(String username,String password,int expire)throws  Exception;

    /**
     * 注册用户并生成 Token
     * @param usernameAndPassword
     * @return
     * @throws Exception
     */
    String registerUserAndGenerateToken(UserNameAndPassword usernameAndPassword)
            throws Exception;


}
