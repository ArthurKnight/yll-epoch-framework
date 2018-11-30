package com.github.yll.epoch.business.admin.service;


import com.github.yll.epoch.business.admin.model.LevelRecord;
import com.github.yll.epoch.business.admin.model.Player;
import com.github.yll.epoch.core.commons.Result;

/**
 * @author luliang_yu
 * @date 2018/11/30
 */
public interface PlayerService {

    Player selectByUniqueKey(String id);

    Player findByUkId(String id);

    Player insertPlayer(Player player);

    String getToken(int id);

    /**
     * 保存关卡信息
     *
     * @param token
     * @param record
     * @return
     */
    Result saveLevelRecord(String token, LevelRecord record);

    Player findById(Integer id);

    /**
     * 登录游戏
     *
     * @param id
     * @param area
     * @param ukId
     * @param head
     * @param loginType
     * @return
     */
    Result login(String id, String area, String ukId, String head, Integer loginType);

    /**
     * 获取玩家信息
     *
     * @param id
     * @param token
     * @return
     */
    Result getPlayerInfo(Integer id, String token);

    /**
     * 获取玩家卡牌
     *
     * @param id
     * @param token
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result getPlayerCard(Integer id, String token, Integer pageNo, Integer pageSize);

    /**
     * 修改卡牌顺序
     *
     * @param id
     * @param token
     * @param cardId
     * @param cardOrder
     * @param changeCardId
     * @param changeCardOrder
     * @return
     */
    Result changeCardOrder(Integer id, String token, Integer cardId, Integer cardOrder, Integer changeCardId, Integer changeCardOrder);

    /**
     * 修改背景
     *
     * @param id
     * @param token
     * @param bgId
     * @return
     */
    Result changeBg(Integer id, String token, Integer bgId);

    /**
     * 获取玩家关卡列表
     *
     * @param id
     * @param token
     * @return
     */
    Result getPlayerLevelList(Integer id, String token);

    /**
     * 获取玩家关卡详情
     *
     * @param id
     * @param token
     * @return
     */
    Result getPlayerLevelInfo(Integer id, String token, Integer productId);

    /**
     * 解锁关卡
     *
     * @param id
     * @param token
     * @param productId
     * @param levelId
     * @return
     */
    Result unlockLevel(Integer id, String token, Integer productId, Integer levelId);

    /**
     * 排行榜-关卡-全服
     *
     * @param id
     * @param token
     * @param productId
     * @param levelId
     * @return
     */
    Result rankingLevelList(Integer id, String token, Integer productId, Integer levelId);

    Result rankingProductList(Integer id, String token, Integer productId);

    Result achivementCardList(Integer id, String token, Integer productId);

    Result achivementCardUnlock(Integer id, String token, Integer productId);

    Result reward(Integer id, String token);
}
