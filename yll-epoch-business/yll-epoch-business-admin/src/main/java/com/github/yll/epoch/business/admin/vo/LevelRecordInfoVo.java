package com.github.yll.epoch.business.admin.vo;

import java.io.Serializable;

/**
 * @author luliang_yu
 * @date 2018-11-26
 */

public class LevelRecordInfoVo implements Serializable {

    private Integer levelId;

    private Integer star;

    private String record;

    private Integer costTime;

    private Integer score;

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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Integer getCostTime() {
        return costTime;
    }

    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
