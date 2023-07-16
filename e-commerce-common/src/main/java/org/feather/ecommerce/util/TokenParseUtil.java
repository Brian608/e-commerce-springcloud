package org.feather.ecommerce.util;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.feather.ecommerce.constants.CommonConstants;
import org.feather.ecommerce.vo.LoginUserInfo;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.util
 * @className: TokenPasrseUtil
 * @author: feather
 * @description:  jwt token 解析工具类
 * @since: 2023-07-16 7:34
 * @version: 1.0
 */

public class TokenParseUtil {




    /**
     * <h2>从 JWT Token 中解析 LoginUserInfo 对象</h2>
     * */
    public static LoginUserInfo parseUserInfoFromToken(String token) throws Exception {

        if (null == token) {
            return null;
        }

        Jws<Claims> claimsJws = parseToken(token, getPublicKey());
        Claims body = claimsJws.getBody();

        // 如果 Token 已经过期了, 返回 null
        if (body.getExpiration().before(Calendar.getInstance().getTime())) {
            return null;
        }

        // 返回 Token 中保存的用户信息
        return JSON.parseObject(
                body.get(CommonConstants.JWT_USER_INFO_KEY).toString(),
                LoginUserInfo.class
        );

    }
    /**
     * <h2>通过公钥去解析 JWT Token</h2>
     * */
    private static Jws<Claims> parseToken(String token, PublicKey publicKey) {

        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 根据本地存储的公钥获取到PublicKey 对象
     * @return
     * @throws Exception
     */
    private  static PublicKey getPublicKey() throws  Exception{

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(CommonConstants.PUBLIC_KEY)
        );
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }
}
