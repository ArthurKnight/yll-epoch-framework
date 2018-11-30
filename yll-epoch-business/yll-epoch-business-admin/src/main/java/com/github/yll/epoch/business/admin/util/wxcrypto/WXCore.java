package com.github.yll.epoch.business.admin.util.wxcrypto;

/**
 * @author luliang_yu
 * @date 2018-11-28
 */


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;


/**
 * 封装对外访问方法
 *
 * @author liuyazhuang
 */
public class WXCore {

    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";

    /**
     * 解密数据
     *
     * @return
     * @throws Exception
     */
    public static String decrypt(String appId, String encryptedData, String sessionKey, String iv) {
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if (null != resultByte && resultByte.length > 0) {
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.parseObject(result);
                System.out.println(jsonObject.toJSONString());
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if (!appId.equals(decryptAppid)) {
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        String appId = "wx4f4bc4dec97d474b";
        String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        String iv = "r7BXXKkLb8qrSNn05n0qiA==";
        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));

//        String appId = "wx0b32356f920643f4";
//        String encryptedData = "rSl9xVEukqQYZpscVN5F9Tn7omIX6mcGAG7E05RDr8xd6xN1hiKtXCWAtfKuMzLoBM6vl5kH3IJHfOPQgukakqUOlwYUV6k2z4RbFBbgiG5EFcgiBV/HPtJqE/LceZ6hEBsu6/dze3IZzfFXlhIF41qWBxXt7q9T X7O59S/433Kedk Ytz SG8GWlaNMlMmXAgp9EL1AO9X6ZRWwKJ 0YWmaeRqtgBWLQf1sMbDOpreCeNAcJwMGId S4VtgS1fWLlJrm 9RLGcPr2VzCtw5OJMb9HG7zO1Cx28F6N6S7aulMxzNVVH1wLf96qrN00wpN  f k6qfCnMNAOB3n9FPp8hnn3swGqT6FQx4L6rXiLgZ7TkohrGkInxapc0jf3gBYNfe9k5DjJU3t5SliwS9YZ5YR4cEcbGOAk6lqd6DIqx9i Cz6cKbRNYR cuO5JFGi28cjxYzrVKkmUvBB2EC/3wFFg6vfFP1Cut3oNqMU=,code=061wcIhW1DQNPU0bHvkW1HAJhW1wcIhp";
//        String sessionKey = "t4tN4L38XFNQ2C1T6EpJPw";
//        String iv = "N9YdgakjM/AtyIExDj9Nrw==";
//        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));

//        String appId = "wx4f4bc4dec97d474b";
//        String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
//        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
//        String iv = "r7BXXKkLb8qrSNn05n0qiA==";
//        //System.out.println(decrypt(appId, encryptedData, sessionKey, iv));//oOgDN4gbcgGZCtZHmksd3t1g0c90
//
//        encryptedData = "CNtSo3kCTxYoaxdhxzmVN/CvIJK41+1DOTZcrXUVMd9+5Z8jTDvbn6kExXRzH+EqWD6oyXwxUzYsW5nyZ7hGAx4pqYylJUzshx+R9DrpQ7HAb5ScH1e0zk//UAiD11W19uFo2/nYyx5ug99jCj6YOgicpfNHSatqkLVRryQlDEzNyaEuo/84uHIpxwC7t8yqmuwUtW2qedtJxWmYWOC1ZQ==";
//        sessionKey= "9QJPlP2TyuDFH1A73pnGqg==";
//        iv = "SABdIDCax6u7H0f6OILGzw==";
//        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));
    }
}
