package com.yd.sdk.smart.util;

import com.yd.sdk.smart.model.ApiRequestDO;
import com.yd.sdk.smart.model.RequestMessage;
import com.yd.sdk.smart.sign.*;

import java.util.HashMap;

/**
 * 类AtopSignUtil.java的实现描述：TODO 类实现描述
 *
 * @author kedumin 2014-7-4 上午12:51:40
 */
public class AtopMobileSignUtil {

    private static HashMap<String, SignAlgorithm>	signAlgorithmMap;

    static {
        signAlgorithmMap = new HashMap<String, SignAlgorithm>();
        signAlgorithmMap.put("md5", new Md5Sign());
        signAlgorithmMap.put("dataMd5", new DataMd5Sign());
        signAlgorithmMap.put("md5Hex", new Md5HexSign());
        signAlgorithmMap.put("pcMd5Hex", new PcSign());
    }

    /**
     * 验证认证,新的签名认证方式 md5(appSecret+签名内容+md5hex(postData)+t) 未登陆流程
     * md5（ecode+appSecret+签名内容+sid+md5hex(postData)+t) 登陆流程
     *
     * @param apiRequestDo
     * @return
     */
    public static boolean singValidate(ApiRequestDO apiRequestDo) {
        String encryptType = apiRequestDo.getAppInfoDo().getEncryptType();
        SignAlgorithm algorithm = signAlgorithmMap.get(encryptType);
        if (algorithm == null) {
            return false;
        }

        String sign = algorithm.getSignString(apiRequestDo);
        if (apiRequestDo.getOpenSign() && !sign.equals(apiRequestDo.getSign().trim())) {
            if (apiRequestDo.getApiContextDo().getOs().equalsIgnoreCase("ios")) {
                if (algorithm instanceof Md5HexSign) {
                    Md5HexSign md5HexAlgorithm = (Md5HexSign) algorithm;
                    String fallbackSign = md5HexAlgorithm.getValidateFallbackString(apiRequestDo);
                    if (fallbackSign.equals(apiRequestDo.getSign().trim())) {
                        return true;
                    } else {
                        System.out.println("singInput=" + algorithm.getSignInput(apiRequestDo) + ",computed sign="
                                + fallbackSign + ",compared sign=" + apiRequestDo.getSign());
                        return false;
                    }
                }
            }
            System.out.println("singInput=" + algorithm.getSignInput(apiRequestDo) + ",computed sign=" + sign
                    + ",compared sign=" + apiRequestDo.getSign());
            return false;
        }
        return true;
    }

    public static String getSign(RequestMessage request, String secretKey) {
        Md5HexSign md5HexAlgorithm = new Md5HexSign();
        String fallbackSign = md5HexAlgorithm.getSign(request,secretKey);
        return fallbackSign;
    }

}
