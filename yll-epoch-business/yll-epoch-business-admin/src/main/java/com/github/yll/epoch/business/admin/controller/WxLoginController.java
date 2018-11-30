package com.github.yll.epoch.business.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.yll.epoch.business.admin.config.WeChatConfig;
import com.github.yll.epoch.business.admin.util.GameHttpClient;
import com.github.yll.epoch.core.commons.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

/**
 * 微信登录
 *
 * @author luliang_yu
 * @date 2018-11-28
 */
@RestController
@RequestMapping(value = "/wx")
@Api(value = "/wx", tags = "微信登录相关", description = "微信登录相关API")
public class WxLoginController {

    private static final Logger logger = LoggerFactory.getLogger(WxLoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    @ApiOperation(value = "微信登录校验获取openid")
    @ResponseBody
    public Result login(
            @RequestParam(value = "encryptedData") String encryptedData,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "iv") String iv) {
        //登录凭证校验，获取openid,session_key
        String result = wxLogin(code);
        JSONObject oppidObj = JSONObject.parseObject(result);
        String openid = (String) oppidObj.get("openid");
        String sessionKey = (String) oppidObj.get("session_key");
        logger.info("openId:{}", openid);
//        String sessionkey = getSessionKey(code);
//        //String jsonStr = WXCore.decrypt(appId1, encryptedData1, sessionkey1, iv1);
//        String jsonStr = WXCore.decrypt(WeChatConfig.APP_ID, encryptedData, sessionkey, iv);
//        JSONObject userInfo = JSONObject.parseObject(jsonStr);
//        logger.info(userInfo.toJSONString());
//        String  ukId =  (String) userInfo.get("openid");
        return Result.createSuccessResult().setData(openid);
    }

    public String wxLogin(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WeChatConfig.APP_ID + "&secret="
                + WeChatConfig.APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String result = GameHttpClient.httpGet(url);
        logger.info(url);
        logger.info(result);
        return result;
    }

    /**
     * 获取信息
     */
    public JSONObject getUserInfo(String encryptedData, String sessionkey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (resultByte.length > 0) {
                String result = new String(resultByte, StandardCharsets.UTF_8);
                return JSONObject.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
