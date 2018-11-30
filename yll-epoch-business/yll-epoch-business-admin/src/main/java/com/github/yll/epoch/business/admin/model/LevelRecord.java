package com.github.yll.epoch.business.admin.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author luliang_yu
 * @date 2018-11-23
 */

public class LevelRecord implements Serializable {

    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiParam(value = "玩家id", required = true)
    private Integer playerId;

    @ApiParam(value = "关卡id", required = true)
    private Integer levelId;

    @ApiParam(value = "作品id", required = true)
    private Integer productId;

    @ApiParam(value = "星星数", required = true)
    private Integer star;

    @ApiParam(value = "关卡记录信息", required = true)
    private String record;

    @ApiParam(value = "关卡耗时ms", required = true)
    private Integer costTime;

    @ApiParam(value = "得分", required = true)
    private Integer score;

    @ApiModelProperty(hidden = true)
    private Date createTimestamp;

    @ApiModelProperty(hidden = true)
    private Date lastChangeTimestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getLastChangeTimestamp() {
        return lastChangeTimestamp;
    }

    public void setLastChangeTimestamp(Date lastChangeTimestamp) {
        this.lastChangeTimestamp = lastChangeTimestamp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LevelRecord.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("playerId=" + playerId)
                .add("levelId=" + levelId)
                .add("productId=" + productId)
                .add("star=" + star)
                .add("record='" + record + "'")
                .add("costTime=" + costTime)
                .add("score=" + score)
                .add("createTimestamp=" + createTimestamp)
                .add("lastChangeTimestamp=" + lastChangeTimestamp)
                .toString();
    }
}
