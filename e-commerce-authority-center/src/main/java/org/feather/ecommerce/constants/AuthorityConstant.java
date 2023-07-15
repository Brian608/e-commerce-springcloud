package org.feather.ecommerce.constants;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.constants
 * @className: AuthorityConstant
 * @author: feather
 * @description: 授权需要使用的一些常量信息
 * @since: 2023-07-14 8:34
 * @version: 1.0
 */
public interface AuthorityConstant {

    /**
     * RSA  私钥，除了授权中心不暴露给任何客户端
     */
    String PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCre7ovm9Q9b5GhASwyWh8nWDBjFhdrgKGmj4hHqnJas8N28Yon8BQt+GnnkZzsUWt1vL4cDlYMUkMv5b+YtvRpybCxElPDciDppZuLAubd+dKuFs1FmisTQm8J7w3Zx+E1X7WfiXZMvhoEQwFSYiJdDjmn5SeCCaOzofJolo5ILxDtrilguHdxGNdV5b1fRZAvrwbDtkqutqL2D1cZZeMRy84qwfieYZwnIWpx46xEwqApiCYs/OSXiFgOPHVdRZM+60H0lYhkBFjlVY0UvaMHa2cB7TGtVcPSSuvchAPpQJTQ8awTmdVBW74IrVI7MNCSZJ7NtlV8vpDLhZiEo7rLAgMBAAECggEAKmIsRHQZYw3eSpMFn3RS2uCbJQJiy3lBeu9i2YOxO+XOW+mbIf/enPYXBtsgk/ATxUffHQQWFHmIjaAVWONvSW5GM526AkaFB/qclSEkepyyi8Cq/5b5Koty8luZashSS2TaeRH6oUEjIQaGnxwOLj3D1lF3EFbdlRpj1aHQjNpdTIw+MkqB8IJwgwRaI7er0oC6seBZr7jUgx/sFi4pDL5ZtK40ehLrujXf7FJSyEp059hGnxfZiewvOstEoBEGbyUX4iVRjxj4W/j3BdZwPitjWw5DICYdANCGqsFhk0xfsnQ7+IcQKB1M47bjLuaser1AKYq5pWtvd/clyaoaMQKBgQDiayI5HvcrPSLHVnUSSsp5MmrFxTIj923BaIcksVVoocdBBWZOw8SxaEv60NKh3J0oAycRe6y6HwpFnK2WwmEg67QVrosH4SjAwWm783ALJU4eEelMoMi9Qzse2Lc1hZcAjY14xkZhicTWCkIHT8WMYYe48GM0WPrCkA/6eL+gzQKBgQDB4za1lRixRZ/hclMYgjrOAHNM7spEPMrSQUDF/I8NX26//P63F/mpE3T2uOVO5LqUuDQoRLq1lbQSxpwPQvZMM72WzrLa+BYsj38pXom2SAbx6VH3N7V/kw1Hw2l8arZFKjNDYQB/5kjI8BqUwt38EZsjJD2uDLR2Fa1Y//np9wKBgCdhqP80cptTs+PmFtDKr3LrIQUrKnB1DeMREkiAi7F6ShT2InhatrCAyo5imfBLVg5qlfYFg+KPmZLIEK1jO8+lD5qh1E7Xi+YS/hgR3JAjufWi+eg5IxFAS10vJpapmL0g4dV67yzx8ImmGRv6dQrYv+mb7jVhsKP8uLrBG9uVAoGBAK/wTN7tkuCaO4DKy1i8JDx28CnpriXJvIqap/qBOhZ6ePdCzqi62tRwUXzpPzweFqmJldrMs1i/pg47p7W5n+J6ORo2KtrEsOpytIOHxBNivNqcTMK3DL3dZPmv+3LpKskMiEQmK9vjzjx4lMZVJjbsiwiShF4CLWfSN+FBNzj1AoGBAJUfgY0MoHDcjt+soSIvuJBeJC84DdlN7IojA/rt/UE2DViwLAMlFdwCe9MwbkEHCJpZfAa808ZT0NTVL7lJweGOw3D+EeWmkdCdeJ7Uzr/HSBQrp8CeqQ3Y0z+OjzvXp0quVQ7joyLopQfACB0C1v2ZP5i7i5wIE15VrSe06thp";




    /**
     * 默认的 Token 超时时间, 一天
     */
    Integer DEFAULT_EXPIRE_DAY = 1;
}
