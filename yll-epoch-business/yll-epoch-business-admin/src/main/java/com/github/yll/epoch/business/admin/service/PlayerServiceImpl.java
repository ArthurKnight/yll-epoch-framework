package com.github.yll.epoch.business.admin.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yll.epoch.business.admin.dao.LevelRecordMapper;
import com.github.yll.epoch.business.admin.dao.PlayerCardMapper;
import com.github.yll.epoch.business.admin.dao.PlayerMapper;
import com.github.yll.epoch.business.admin.dao.PlayerRewardMapper;
import com.github.yll.epoch.business.admin.model.*;
import com.github.yll.epoch.business.admin.util.DateSubUtil;
import com.github.yll.epoch.business.admin.vo.LevelRankVo;
import com.github.yll.epoch.business.admin.vo.LevelRecordInfoVo;
import com.github.yll.epoch.business.admin.vo.LevelRecordVo;
import com.github.yll.epoch.core.commons.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author luliang_yu
 * @date 2018-11-21
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    PlayerMapper playerMapper;

    @Autowired
    LevelRecordMapper levelRecordMapper;

    @Autowired
    PlayerCardMapper playerCardMapper;

    @Autowired
    PlayerRewardMapper playerRewardMapper;

//    @Autowired
//    private CacheManager cacheManager;

    @Override
    public Player selectByUniqueKey(String id) {
        return playerMapper.selectByUniqueKey(id);
    }

    @Override
    public Player findByUkId(String id) {
        return playerMapper.findByUkId(id);
    }

    @Override
    public Player insertPlayer(Player player) {
        playerMapper.insertPlayer(player);
        return player;
    }

    private boolean validToken(Integer id, String token) {
        return playerMapper.validToken(id, token) != null;
    }

    @Override
    @Cacheable(value = "token", key = "#id")
    public String getToken(int id) {
        //logger.info("{} 使用到的Cachemanager is {}", id, cacheManager);
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        playerMapper.updateToken(id, token);
        logger.info("cache is {}", token);
        return token;
    }

    @Override
    public Result saveLevelRecord(String token, LevelRecord record) {
        if (validToken(record.getPlayerId(), token)) {
            LevelRecord levelRecord = levelRecordMapper.findRecord(record);
            Date now = new Date();
            if (levelRecord == null) {
                record.setCreateTimestamp(now);
                record.setLastChangeTimestamp(now);
                levelRecordMapper.insert(record);
            } else {
                record.setCreateTimestamp(levelRecord.getCreateTimestamp());
                record.setLastChangeTimestamp(now);
                levelRecordMapper.updateRecord(record);
            }
            return Result.createSuccessResult();
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Player findById(Integer id) {
        Player player = playerMapper.findById(id);
        return player;
    }

    @Override
    public Result login(String ukId, String area, String name, String head, Integer loginType) {
        Player p = playerMapper.findByUkId(ukId);
        if (p == null) {
            logger.info("[create player ukid:{}]", ukId);
            p = new Player();
            p.setArea(area);
            Date now = new Date();
            p.setCreateTimestamp(now);
            p.setLastChangeTimestamp(now);
            p.setHead(head);
            p.setHeadFrame(0);
            p.setName(name);
            p.setSadou(0);
            p.setStar(0);
            p.setBg(0);
            p.setUkId(ukId);
            p.setLoginType(loginType);
            playerMapper.insertPlayer(p);
        } else {
            Date now = new Date();
            p.setLastChangeTimestamp(now);
            p.setHeadFrame(0);
            p.setName(name);
            p.setArea(area);
            playerMapper.update(p);
        }
        p.setToken(getToken(p.getId()));
        Map<String, Object> map = new HashMap<>();
        map.put("id", p.getId());
        map.put("token", p.getToken());
        return Result.createSuccessResult().setData(map);
    }

    @Override
    public Result getPlayerInfo(Integer id, String token) {
        if (validToken(id, token)) {
            long bTime = System.currentTimeMillis();
            Player p = playerMapper.findById(id);
            Integer star = playerMapper.getTotalStar(id);
            p.setStar(star);
            long eTime = System.currentTimeMillis();
            if (eTime - bTime > 1000) {
                logger.warn("获取玩家信息耗时：{}ms", eTime - bTime);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("playerInfo", p);
            return Result.createSuccessResult().setData(map);
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result getPlayerCard(Integer id, String token, Integer pageNo, Integer pageSize) {
        if (validToken(id, token)) {
            PageHelper.startPage(pageNo, pageSize);
            List<PlayerCard> cards = playerCardMapper.getCards(id);
            PageInfo<PlayerCard> pageInfo = new PageInfo<>(cards);
            Map<String, Object> map = new HashMap<>();
            map.put("cards", cards);
            map.put("total", pageInfo.getTotal());
            map.put("pages", pageInfo.getPages());
            return Result.createSuccessResult().setData(map);
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result changeCardOrder(Integer id, String token, Integer cardId, Integer cardOrder, Integer changeCardId, Integer changeCardOrder) {
        if (validToken(id, token)) {
            playerCardMapper.changeCardOrder(id, cardId, cardOrder, changeCardId, changeCardOrder);
            return Result.createSuccessResult();
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result changeBg(Integer id, String token, Integer bgId) {
        if (validToken(id, token)) {
            playerMapper.changeBg(id, bgId);
            return Result.createSuccessResult();
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result getPlayerLevelList(Integer id, String token) {
        if (validToken(id, token)) {
            List<LevelRecordVo> levelRecords = levelRecordMapper.getLevelRecordList(id);
            return Result.createSuccessResult().setData(levelRecords);
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result getPlayerLevelInfo(Integer id, String token, Integer productId) {
        if (validToken(id, token)) {
            List<LevelRecordInfoVo> levelRecords = levelRecordMapper.getLevelRecord(id, productId);
            return Result.createSuccessResult().setData(levelRecords);
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result unlockLevel(Integer id, String token, Integer productId, Integer levelId) {
        if (validToken(id, token)) {
            Player player = playerMapper.findById(id);
            int num = 20;
            if (player.getSadou() < num) {
                return Result.create(2).setMsg("飒豆余额不足");
            }
            playerMapper.updateSadou(id, num);
            LevelRecord record = new LevelRecord();
            record.setPlayerId(id);
            record.setLevelId(levelId);
            record.setProductId(productId);
            record.setStar(0);
            record.setRecord("");
            record.setCostTime(0);
            record.setScore(0);
            Date date = new Date();
            record.setCreateTimestamp(date);
            record.setLastChangeTimestamp(date);
            levelRecordMapper.insert(record);
            return Result.createSuccessResult();
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result rankingLevelList(Integer id, String token, Integer productId, Integer levelId) {
        if (validToken(id, token)) {
            List<LevelRankVo> levelRecords = levelRecordMapper.rankingLevelList(productId, levelId);
            return Result.createSuccessResult().setData(levelRecords);
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result rankingProductList(Integer id, String token, Integer productId) {
        if (validToken(id, token)) {
            List<LevelRankVo> levelRecords = levelRecordMapper.rankingProductList(productId);
            return Result.createSuccessResult().setData(levelRecords);
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result achivementCardList(Integer id, String token, Integer productId) {
        if (validToken(id, token)) {
            List<PlayerCard> levelRecords = levelRecordMapper.achivementCardList(id, productId);
            return Result.createSuccessResult().setData(levelRecords);
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result achivementCardUnlock(Integer id, String token, Integer productId) {
        if (validToken(id, token)) {
            List<Card> cards = playerCardMapper.getRandomCards(id, productId);
            if (cards.size() == 0) {
                return Result.create(2).setMsg("已解锁所有卡牌");
            }
            //星星数是否满足
            //int cardsNum = playerCardMapper.countCards(id, productId);
            //随机一张卡牌
            int index = (int) (Math.random() * cards.size());
            Card card = cards.get(index);
            //最大卡牌序号
            int maxorder = playerCardMapper.getMaxCardOrder(id) + 1;
            //插入到player_card表
            PlayerCard playerCard = new PlayerCard();
            playerCard.setCardId(card.getId());
            playerCard.setCardOrder(maxorder);
            playerCard.setCardPng(card.getCardPng());
            playerCard.setGetTime(new Date());
            playerCard.setPlayerId(id);
            playerCard.setProductId(productId);
            playerCardMapper.insert(playerCard);
            return Result.createSuccessResult().setData(playerCard);
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }

    @Override
    public Result reward(Integer id, String token) {
        if (validToken(id, token)) {
            PlayerReward playerReward = playerRewardMapper.get(id);
            List<Share> shareList = playerRewardMapper.getShareList();
            //未领过奖励
            if (playerReward == null) {
                Share share = shareList.get(0);
                String items = share.getItemId();
                String[] item = items.split(",");
                if (Integer.valueOf(item[0]) == 1) {
                    int sadou = Integer.valueOf(item[1]);
                    int delayTime = share.getDelayTime();
                    playerMapper.updateAddSadou(id, sadou);
                    PlayerReward reward = new PlayerReward();
                    reward.setLastRewardTimestamp(new Date());
                    reward.setPlayerId(id);
                    reward.setTimes(1);
                    playerRewardMapper.insert(reward);
                    return Result.createSuccessResult().setData(items);
                }
                return Result.create(2).setMsg("其他itemid 未处理");
            } else {
                //领奖次数
                int times = playerReward.getTimes();
                //领奖间隔
                int deplayTime = shareList.get(times - 1).getDelayTime();
                //上次领奖时间
                long lastRewardTime = playerReward.getLastRewardTimestamp().getTime();
                //可领奖时间
                long reTime = lastRewardTime + deplayTime * 60 * 1000;
                //当前时间
                long currentTime = System.currentTimeMillis();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logger.info("领奖时间：{}，次数{}", sdf.format(reTime), times);
                logger.info("当前时间：{}", sdf.format(currentTime));
                //凌晨0点刷新次数
                if (DateSubUtil.getDaySub(lastRewardTime, currentTime) >= 1) {
                    playerReward.setTimes(0);
                    playerRewardMapper.update(playerReward);
                    times = 0;
                }
                if (times < 4) {
                    Share rewardShare = shareList.get(times);
                    if (currentTime - reTime > 0) {
                        String items = rewardShare.getItemId();
                        String[] item = items.split(",");
                        if (Integer.valueOf(item[0]) == 1) {
                            int sadou = Integer.valueOf(item[1]);
                            playerMapper.updateAddSadou(id, sadou);
                            PlayerReward reward = new PlayerReward();
                            reward.setLastRewardTimestamp(new Date());
                            reward.setPlayerId(id);
                            reward.setTimes(playerReward.getTimes() + 1);
                            playerRewardMapper.update(reward);
                            return Result.createSuccessResult().setData(items);
                        } else {
                            return Result.create(2).setMsg("其他itemid 未处理");
                        }
                    } else {
                        return Result.createSuccessResult().setMsg("未到时间无法领取");
                    }
                } else {
                    return Result.create(1).setData("已领完当天奖励");
                }
            }
        } else {
            return Result.create(1).setMsg("token 验证失败");
        }
    }


}
