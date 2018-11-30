package com.github.yll.epoch.business.admin.controller;

import com.github.yll.epoch.business.admin.service.PlayerService;
import com.github.yll.epoch.core.commons.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 成就
 *
 * @author luliang_yu
 * @date 2018-11-26
 */
@RestController
@RequestMapping(value = "/achivement")
@Api(value = "/achivement", tags = "成就", description = "成就相关API")
public class AchievementController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/card/list", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "根据作品id和玩家id获取玩家卡牌列表")
    @ResponseBody
    public Result achivementCardList(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            @RequestParam(value = "productId") @ApiParam(value = "作品id") Integer productId) {
        Result result = playerService.achivementCardList(id, token, productId);
        return result;
    }

    @RequestMapping(value = "/card/unlock", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "根据作品id和玩家id随机解锁一张卡牌")
    @ResponseBody
    public Result achivementCardUnlock(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            @RequestParam(value = "productId") @ApiParam(value = "作品id") Integer productId) {
        Result result = playerService.achivementCardUnlock(id, token, productId);
        return result;
    }
}
