package com.github.yll.epoch.business.admin.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 游戏服务Http客户端
 *
 * @author luliang_yu
 * @date 2018/11/27
 */
public class GameHttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameHttpClient.class);
    private static final String DEFAULT_CHARSET = "UTF-8";
    public static final String RESULT_DATA = "data";
    public static final String RESULT_CODE = "code";
    /**
     * 结果码-服务端响应超时
     */
    public static int RESULT_CODE_TIMEOUT = -1;
    /**
     * 结果码-其他异常错误
     */
    public static int RESULT_CODE_OTHER_ERROR = -2;

    /**
     * 游戏HTTPServer主机地址
     */
    private String httpServerHost;
    /**
     * 游戏HTTPServer端口号
     */
    private int httpServerPort;

    /**
     * HTTP客户端
     */
    private CloseableHttpClient httpClient;
    /**
     * Http请求配置
     */
    private RequestConfig requestConfig;

    /**
     * 构造方法
     */
    public GameHttpClient() {
        super();
        this.httpClient = HttpClients.createDefault();
        this.requestConfig = RequestConfig.custom().build();//设置请求和传输超时时间
    }

    /**
     * 构造方法
     *
     * @param host 服务地址
     * @param port 端口号
     */
    public GameHttpClient(String host, int port) {
        this();
        this.httpServerHost = host;
        this.httpServerPort = port;
    }

    /**
     * get请求
     *
     * @param url url
     * @return 响应流
     * @throws IOException
     */
    public static String httpGet(String url/*, String token*/) {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        //httpGet.setHeader("token", token);
        return execute(client, httpGet);
    }

    /**
     * 执行请求并响应
     *
     * @param client   client
     * @param httpPost httpPost
     * @return 结果流字符串
     */
    private static String execute(CloseableHttpClient client, HttpRequestBase httpPost) {
        if (client == null || httpPost == null) {
            return "";
        }
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                return EntityUtils.toString(resEntity);
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    /**
     * 敏感词过滤
     *
     * @param word
     * @return
     */
    public Map<String, Object> webkeyfilter(String word) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("word", word);

        //向服务端发送请求
        Map<String, Object> resMap = this.send("/webkeyfilter", JSONObject.toJSONString(params));

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(RESULT_CODE, resMap.get(RESULT_CODE));

        String content = (String) resMap.get(RESULT_DATA);
        JSONObject obj = JSONObject.parseObject(content);
        result.put("word", obj.getString("word"));
        result.put("result", obj.getString("result"));

        return result;
    }

    /**
     * web服务端消耗物品
     *
     * @param playerId     玩家ID
     * @param privateToken 私有令牌
     * @param itemId       物品ID
     * @param itemNum      消耗数量
     * @return
     */
    public Map<String, Object> webuseitem(Integer playerId, String privateToken, int itemId, int itemNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("playerid", playerId);
        params.put("token", privateToken);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", itemId);
        data.put("num", itemNum);
        params.put("data", data);

        //向服务端发送请求
        Map<String, Object> resMap = this.send("/webuseitem", JSONObject.toJSONString(params));

        return resMap;
    }

    /**
     * web服务端请求随机奖励
     *
     * @param playerId     玩家ID
     * @param privateToken 私有令牌
     * @return
     */
    public Map<String, Object> webreward(Integer playerId, String privateToken, Integer rewardCount, Integer rewardTotal) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("playerid", playerId);
        params.put("token", privateToken);
        params.put("reward_count", rewardCount);
        params.put("reward_total", rewardTotal);

        Map<String, Object> data = new HashMap<String, Object>(1);
        params.put("data", data);

        //向服务端发送请求
        Map<String, Object> resMap = this.send("/webreward", JSONObject.toJSONString(params));
        LOGGER.info("web服务端请求随机奖励响应结果:" + resMap);
        return resMap;
    }

    /**
     * 刷新活动
     *
     * @return
     */
    public Map<String, Object> webnewactivity() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", "eeffaabbed23deaaaa");
        Map<String, Object> data = new HashMap<String, Object>();
        params.put("data", data);
        //向服务端发送请求
        Map<String, Object> resMap = this.send("/webnewactivity", JSONObject.toJSONString(params));
        return resMap;
    }

    /**
     * 原创广场奖励
     *
     * @param playerId
     * @param privateToken
     * @return
     */
    public Map<String, Object> webadditem(Integer playerId, String privateToken, String reward) {
        StringBuffer data = new StringBuffer("[");
        String[] temp = reward.split(";");
        for (String s : temp) {
            String[] d = s.split(",");
            data.append("{\"id\":").append(d[0]).append(",\"num\":").append(d[1]).append("},");
        }
        String str = data.substring(0, data.length() - 1);
        str = str + "]";
        System.err.println(str);
        String params = "{\"playerid\":" + playerId + ",\"token\":\"" + privateToken + "\",\"data\":" + str + "}";
        //向服务端发送请求
        Map<String, Object> resMap = this.send("/webadditem", params);
        return resMap;
    }

    /**
     * 获取玩家相关信息
     *
     * @param token
     * @param playerId
     * @return
     */
    public Map<String, Object> getPlayerInfo(String token, Integer playerId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("playerid", playerId);
        if (token != null) {
            params.put("token", token);
        } else {
            params.put("token", "aa");
        }
        params.put("data", "");
        Map<String, Object> map = this.send("/webinfo", JSONObject.toJSONString(params));
        return map;
    }

    /**
     * 玩家背包
     *
     * @param token
     * @param playerId
     * @return
     */
    public Map<String, Object> playerBag(String token, Integer playerId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("playerid", playerId);
        params.put("token", token);
        params.put("data", "");
        Map<String, Object> map = this.send("/webinfo", JSONObject.toJSONString(params));
        Map<String, Object> resMap = new HashMap<String, Object>();
        if ("200".equals(String.valueOf(map.get("code")))) {
            resMap = this.send("/webprop", JSONObject.toJSONString(params));
        } else {
            resMap.put("code", RESULT_CODE_OTHER_ERROR);
        }
        return resMap;
    }

    /**
     * 玩家封号/解封处理
     *
     * @param playerId
     * @param token
     * @param time     ：封号 时间（小时）
     * @param reason
     * @param type     :1=封号；3=解封；
     * @return
     */
    public Map<String, Object> banPlayer(Integer playerId, String token, Integer time, String reason, Integer type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("playerid", playerId);
        params.put("token", token);
        params.put("data", "");
        Map<String, Object> map = this.send("/webinfo", JSONObject.toJSONString(params));
        Map<String, Object> resMap = new HashMap<String, Object>();
        if ("200".equals(String.valueOf(map.get("code")))) {
            params.put("time", time);
            params.put("type", type);
            params.put("reason", reason);
            params.put("request_player", "");
            resMap = this.send("/webban", JSONObject.toJSONString(params));
        } else {
            resMap.put("code", RESULT_CODE_OTHER_ERROR);
        }
        return resMap;
    }

    /**
     * 查询账户的安全信息
     *
     * @param account：账户名全名
     * @return
     */
    public Map<String, Object> accountInfo(String account) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("token", "alks");
        params.put("data", "");
        Map<String, Object> resMap = this.send("/webaccountinfo", JSONObject.toJSONString(params));
        return resMap;
    }

    /**
     * 道具发放请求
     *
     * @param type:指定玩家类型，目前值只有1（枚举型）
     * @param playerid：玩家id数组
     * @param propid：道具id数组
     * @param number：发放道具数量
     * @param reason：发放理由
     * @return
     */
    public Map<String, Object> sendProp(Byte type, Integer[] playerid, Integer[] propid, Integer[] number, String reason) {
        Map<String, Object> params = new HashMap<String, Object>();
        List<Map<String, Integer>> prop = new ArrayList<Map<String, Integer>>();
        for (int i = 0; i < propid.length; i++) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("id", propid[i]);
            map.put("num", number[i]);
            prop.add(map);
        }
        params.put("token", "aaaccc");
        params.put("request_player", "");
        params.put("data", "");
        params.put("type", type);
        params.put("playerid", playerid);
        params.put("prop", prop);
        params.put("content", reason);
        Map<String, Object> resMap = this.send("/websysmail", JSONObject.toJSONString(params));
        return resMap;
    }


    /**
     * 在线人数统计
     *
     * @return
     */
    public Map<String, Object> onlineStatistic() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", "cc");
        params.put("data", "");
        Map<String, Object> resMap = this.send("/webbaseplayer", JSONObject.toJSONString(params));
        return resMap;
    }

    /**
     * 通过账号名查看玩家的账号信息
     *
     * @return
     */
    public Map<String, Object> webaccountinfo(String text, Integer type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", "dddd");
        params.put("data", "");
        switch (type) {
            case 0:
                params.put("account", text);
                break;
            case 1:
                params.put("email", text);
                break;
            case 2:
                params.put("mobile", text);
                break;
            case 3:
                params.put("accountid", Integer.parseInt(text));
                break;
        }
        Map<String, Object> resMap = this.send("/webaccountinfo", JSONObject.toJSONString(params));
        return resMap;
    }

    /**
     * 将玩家从内存中剔除
     *
     * @param playerId
     * @return
     */
    public Map<String, Object> clearMemory(Integer playerId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", "dddd");
        params.put("data", "");
        params.put("playerid", playerId);
        Map<String, Object> resMap = this.send("/weberaseplayer", JSONObject.toJSONString(params));
        return resMap;
    }


    /**
     * 查询空间吐槽
     *
     * @return
     */
    public Map<String, Object> queryLeaveMessage(Integer playerId, Integer sendId, Integer pageNum, Integer count) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (playerId != null) {
            params.put("playerid", playerId);
        }
        if (sendId != null) {
            params.put("sendid", sendId);
        }
        params.put("token", "dddd");
        params.put("data", "");
        params.put("pagenum", pageNum);
        params.put("count", count);
        System.out.println(JSONObject.toJSONString(params));
        Map<String, Object> resMap = this.send("/queryleavemessage", JSONObject.toJSONString(params));
        return resMap;
    }

    public void deleteSpitslotByID(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", "dddd");
        params.put("data", "");
        params.put("id", id);
        this.send("/deleteleavemessage", JSONObject.toJSONString(params));
    }

    /**
     * 通知游戏服务端，商品信息已经更新，重新加载商品信息
     *
     * @param version 商品数据版本号
     * @return
     */
    public Map<String, Object> webreloadCfg(Long version, String ossBase) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", "eeffaabbed23deaaaa");
        params.put("requester", "fmy-game-admin");
        params.put("data", "");
        params.put("version", version);
        params.put("type_name", "type_mall");
        params.put("type_url", ossBase + "/shop/good/myss_shop_goods.json");
        params.put("res_url", "/shop/good/updateCallback");
        Map<String, Object> resMap = this.send("/webreload_cfg", JSONObject.toJSONString(params));
        return resMap;
    }

    /**
     * 重置玩家账号信息
     *
     * @return
     */
    public Map<String, Object> resetAccount(Integer id, Boolean resetPassword, Boolean resetIdcard, Boolean resetEmail, Boolean resetPhone) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", "eeffaabbed23deaaaa");
        params.put("id", id);
        params.put("data", "");
        if (resetPassword) {
            params.put("psw", "111111");
        }
        if (resetIdcard) {
            params.put("idcard", "");
        }
        if (resetEmail) {
            params.put("email", "");
        }
        if (resetPhone) {
            params.put("mobile", "");
        }
        Map<String, Object> resMap = this.send("/set_account", JSONObject.toJSONString(params));
        return resMap;
    }

    /**
     * 向服务端发送请求
     *
     * @param serviceUri 服务URI
     * @param content    请求参数内容
     * @return 响应结果
     */
    public Map<String, Object> send(String serviceUri, String content) {
        return send(serviceUri, content, DEFAULT_CHARSET);
    }


    /**
     * 向服务端发送请求
     *
     * @param serviceUri 服务URI
     * @param content    请求参数内容
     * @param charset    字符集
     * @return 响应结果
     */
    public Map<String, Object> send(String serviceUri, String content, String charset) {
        String url = "http://" + this.httpServerHost + ":" + this.httpServerPort + serviceUri;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            HttpResponse response = this.doPost(url, content);
            if (response != null) {
                int code = response.getStatusLine().getStatusCode();
                resultMap.put(RESULT_CODE, code);

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String result = EntityUtils.toString(resEntity, charset);
                    resultMap.put(RESULT_DATA, result);
                }
            }
        } catch (SocketTimeoutException ste) {
            resultMap.put(RESULT_CODE, RESULT_CODE_TIMEOUT);
            LOGGER.error("向游戏服务端发送Http请求，服务端处理超时", ste);
        } catch (Exception e) {
            resultMap.put(RESULT_CODE, RESULT_CODE_OTHER_ERROR);
            LOGGER.error("向游戏服务端发送Http请求出现异常", e);
        }
        return resultMap;
    }

    /**
     * HttpClient 发送post请求
     *
     * @param url
     * @param content
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    private HttpResponse doPost(String url, String content) throws ClientProtocolException, IOException {
        long st = System.currentTimeMillis();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(new StringEntity(content, Charset.forName(DEFAULT_CHARSET)));
        HttpResponse response = httpClient.execute(httpPost);
        long et = System.currentTimeMillis();
        LOGGER.info("游戏端HTTP服务响应时间:{}耗秒;服务URL:{};参数:{}", (et - st), url, content);
        return response;
    }

    /**
     * 对象销毁方法，关闭HttpClient
     */
    public void destroy() {
        try {
            this.httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHttpServerHost() {
        return httpServerHost;
    }

    public void setHttpServerHost(String httpServerHost) {
        this.httpServerHost = httpServerHost;
    }

    public int getHttpServerPort() {
        return httpServerPort;
    }

    public void setHttpServerPort(int httpServerPort) {
        this.httpServerPort = httpServerPort;
    }

    public static void main(String[] args) {
        GameHttpClient client = new GameHttpClient("192.168.0.23", 8888);
        for (int i = 0; i < 10000; i++) {
            long bTime = System.currentTimeMillis();
            Map<String, Object> map = null;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("account", "yululiang");
            params.put("pwd", "nl5nZ2kRLnpESP6g");
            map = client.send("/fmy-gengine-login/login/account/signup", JSONObject.toJSONString(params));
            // map = client.resetAccount(417120, true, true, true, true);
            System.out.println(map);
            System.out.println(i + "---" + (System.currentTimeMillis() - bTime) + "ms");
        }

    }

}
