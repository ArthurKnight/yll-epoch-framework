package com.github.yll.epoch.business.admin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 玩家分享获得奖励
 *
 * @author luliang_yu
 * @date 2018-11-28
 */

public class PlayerReward implements Serializable {

    private Integer playerId;

    private Date lastRewardTimestamp;

    private Integer times;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Date getLastRewardTimestamp() {
        return lastRewardTimestamp;
    }

    public void setLastRewardTimestamp(Date lastRewardTimestamp) {
        this.lastRewardTimestamp = lastRewardTimestamp;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
