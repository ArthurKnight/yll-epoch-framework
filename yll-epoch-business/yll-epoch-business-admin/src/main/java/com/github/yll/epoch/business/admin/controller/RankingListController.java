package com.github.yll.epoch.business.admin.controller;

import com.github.yll.epoch.business.admin.service.PlayerService;
import com.github.yll.epoch.core.commons.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 排行榜
 *
 * @author luliang_yu
 * @date 2018-11-26
 */
@RestController
@RequestMapping(value = "/rangking")
@Api(value = "/rangking", tags = "排行榜", description = "排行榜相关API")
public class RankingListController {

    @Autowired
    PlayerService playerService;


    @RequestMapping(value = "/level/list", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "排行榜-关卡-全服排行")
    @ResponseBody
    public Result rankingLevelList(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            @RequestParam(value = "productId") @ApiParam(value = "作品id") Integer productId,
            @RequestParam(value = "levelId") @ApiParam(value = "关卡id") Integer levelId
    ) {
        Result result = playerService.rankingLevelList(id, token, productId, levelId);
        return result;
    }


    @RequestMapping(value = "/product/list", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiOperation(value = "排行榜-作品-全服排行")
    @ResponseBody
    public Result rankingProductList(
            @RequestParam(value = "id") @ApiParam(value = "玩家id") Integer id,
            @RequestParam(value = "token") @ApiParam(value = "token") String token,
            @RequestParam(value = "productId", required = false) @ApiParam(value = "作品id") Integer productId
    ) {
        Result result = playerService.rankingProductList(id, token, productId);
        return result;
    }
}
