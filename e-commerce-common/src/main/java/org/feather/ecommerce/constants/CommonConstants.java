package org.feather.ecommerce.constants;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.constants
 * @className: CommonSonstants
 * @author: feather
 * @description: TODO
 * @since: 2023-07-14 8:39
 * @version: 1.0
 */
public interface CommonConstants {

    /**
     * RSA  公钥
     */
    String PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq3u6L5vUPW+RoQEsMlofJ1gwYxYXa4Chpo+IR6pyWrPDdvGKJ/AULfhp55Gc7FFrdby+HA5WDFJDL+W/mLb0acmwsRJTw3Ig6aWbiwLm3fnSrhbNRZorE0JvCe8N2cfhNV+1n4l2TL4aBEMBUmIiXQ45p+Unggmjs6HyaJaOSC8Q7a4pYLh3cRjXVeW9X0WQL68Gw7ZKrrai9g9XGWXjEcvOKsH4nmGcJyFqceOsRMKgKYgmLPzkl4hYDjx1XUWTPutB9JWIZARY5VWNFL2jB2tnAe0xrVXD0krr3IQD6UCU0PGsE5nVQVu+CK1SOzDQkmSezbZVfL6Qy4WYhKO6ywIDAQAB";


    /**
     * jwt 中存储用户信息的key
     */
    String JWT_USER_INFO_KEY="e-commerce-user";


    /**
     * 授权中心的service-id
     */
    String AUTHORITY_CENTER_SERVICE_ID="e-commerce-authority-center";

}
