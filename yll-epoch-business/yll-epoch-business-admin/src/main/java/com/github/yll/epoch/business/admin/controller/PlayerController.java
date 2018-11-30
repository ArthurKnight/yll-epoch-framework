package com.github.yll.epoch.business.admin.controller;

import com.github.yll.epoch.business.admin.model.LevelRecord;
import com.github.yll.epoch.business.admin.model.Player;
import com.github.yll.epoch.business.admin.service.PlayerService;
import com.github.yll.epoch.core.commons.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author luliang_yu
 * @date 2018-11-07
 */

@RestController
@RequestMapping(value = "/player")
@Api(value = "/player", tags = "进入游戏", description = "游戏相关API")
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerService playerService;


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "登录游戏")
    @ResponseBody
    public Result login(
            @RequestParam(value = "ukId") @ApiParam(value = "唯一id") String ukId,
            @RequestParam(value = "name") @ApiParam(value = "昵称") String name,
            @RequestParam(value = "area") @ApiParam(value = "地区") String area,
            @RequestParam(value = "head") @ApiParam(value = "头像") String head,
            @RequestParam(value = "loginType") @ApiParam(value = "登录类型（微信：0;手q：1）") Integer loginType) {
        Result result = playerService.login(ukId, area, name, head, loginType);
        return result;
    }


    /*@RequestMapping(value = "/info", method = RequestMethod.POST,consumes = "application/x-www-form-urlencoded",produces = "application/json")
    @ApiOperation(value = "qq进入游戏")
    @ResponseBody
    public Result enterGame(
            @RequestParam(value = "ukId") String ukId,
            @RequestParam(value = "area") String area,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "headFrame") Integer headFrame) {
        Result result = playerService.login(ukId, area, name, headFrame, 1);
        return result;
    }*/

    @RequestMapping(value = "/getToken", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "获取Token")
    @ResponseBody
    public Result getToken(@RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id) {
        if (id == null) {
            return Result.createErrorResult();
        }
        Player player = playerService.findById(id);
        if (player == null) {
            return Result.createErrorResult();
        }
        String token = playerService.getToken(id);
        return Result.createSuccessResult().setData(token);
    }

    @RequestMapping(value = "/record", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "保存玩家关卡记录")
    @ResponseBody
    public Result levelRecord(
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            LevelRecord record) {
        Result result = playerService.saveLevelRecord(token, record);
        return result;
    }

    @RequestMapping(value = "/getPlayerInfo", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "个人主页-获取玩家信息")
    @ResponseBody
    public Result getPlayerInfo(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token) {
        Result result = playerService.getPlayerInfo(id, token);
        return result;
    }

    @RequestMapping(value = "/getPlayerCard", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "个人主页-获取玩家卡牌信息")
    @ResponseBody
    public Result getPlayerCard(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            @RequestParam(value = "pageNo", defaultValue = "1") @ApiParam(value = "当前页码") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(value = "每页显示pageSize条数据") Integer pageSize) {
        Result result = playerService.getPlayerCard(id, token, pageNo, pageSize);
        return result;
    }

    @RequestMapping(value = "/changeBg", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "个人主页-修改背景")
    @ResponseBody
    public Result changeBg(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            @RequestParam(value = "bgId") @ApiParam(value = "背景id") Integer bgId) {
        Result result = playerService.changeBg(id, token, bgId);
        return result;
    }

    @RequestMapping(value = "/changeCardOrder", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "个人主页-修改卡牌顺序")
    @ResponseBody
    public Result changeCardOrder(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            @RequestParam(value = "cardId") @ApiParam(value = "卡牌id") Integer cardId,
            @RequestParam(value = "cardOrder") @ApiParam(value = "卡牌序号") Integer cardOrder,
            @RequestParam(value = "changeCardId") @ApiParam(value = "被调换卡牌id") Integer changeCardId,
            @RequestParam(value = "changeCardOrder") @ApiParam(value = "被调换卡牌序号") Integer changeCardOrder) {
        Result result = playerService.changeCardOrder(id, token, cardId, cardOrder, changeCardId, changeCardOrder);
        return result;
    }

    @RequestMapping(value = "/getPlayerLevelList", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "旅程-玩家作品列表")
    @ResponseBody
    public Result getPlayerLevelList(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token) {
        Result result = playerService.getPlayerLevelList(id, token);
        return result;
    }

    @RequestMapping(value = "/getPlayerLevelInfo", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "旅程-玩家关卡列表")
    @ResponseBody
    public Result getPlayerLevelInfo(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            @RequestParam(value = "productId") @ApiParam(value = "作品id") Integer productId) {
        Result result = playerService.getPlayerLevelInfo(id, token, productId);
        return result;
    }

    @RequestMapping(value = "/unlockLevel", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "旅程-通过飒豆解锁关卡")
    @ResponseBody
    public Result unlockLevel(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            @RequestParam(value = "productId") @ApiParam(value = "作品id") Integer productId,
            @RequestParam(value = "levelId") @ApiParam(value = "关卡id") Integer levelId) {
        Result result = playerService.unlockLevel(id, token, productId, levelId);
        return result;
    }

    @RequestMapping(value = "/reward", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "心愿箱-领取收益（0点刷新）")
    @ResponseBody
    public Result reward(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "token") String token) {
        Result result = playerService.reward(id, token);
        return result;
    }

    /*@RequestMapping(value = "/wxinfo", method = RequestMethod.POST,consumes = "application/x-www-form-urlencoded")
    @ApiOperation(value = "微信进入游戏")
    @ResponseBody
    public Result wxinfo(
            @RequestParam(value = "encryptedData") String encryptedData,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "iv") String iv,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "area") String area,
            @RequestParam(value = "headFrame") Integer headFrame) {
        logger.info("iv={}",iv);
        logger.info("encryptedData={}",encryptedData);
        logger.info("code={}",code);
        logger.info("appid={}", WeChatConfig.APP_ID);
        String sessionkey = getSessionKey(code);
        //String jsonStr = WXCore.decrypt(appId1, encryptedData1, sessionkey1, iv1);
        String jsonStr = WXCore.decrypt(WeChatConfig.APP_ID, encryptedData, sessionkey, iv);
        JSONObject userInfo = JSONObject.parseObject(jsonStr);
        logger.info(userInfo.toJSONString());
        String  ukId =  (String) userInfo.get("openid");
        logger.info("openid:"+ukId);
        Result result = playerService.login(ukId, area, name, headFrame, 0);
        return result;
    }*/

}
