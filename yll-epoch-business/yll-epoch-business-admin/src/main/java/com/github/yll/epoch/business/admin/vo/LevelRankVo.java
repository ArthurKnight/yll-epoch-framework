package com.github.yll.epoch.business.admin.vo;

import java.io.Serializable;

/**
 * 关卡排行榜
 *
 * @author luliang_yu
 * @date 2018-11-26
 */

public class LevelRankVo implements Serializable {

    private String name;

    private String head;

    private Integer headFrame;

    private String area;

    private Integer playerId;

    private Integer levelId;

    private Integer star;

    private Integer score;

    private Integer costTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Integer getHeadFrame() {
        return headFrame;
    }

    public void setHeadFrame(Integer headFrame) {
        this.headFrame = headFrame;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCostTime() {
        return costTime;
    }

    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }
}
