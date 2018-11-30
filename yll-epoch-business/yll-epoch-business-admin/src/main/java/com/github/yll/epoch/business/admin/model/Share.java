package com.github.yll.epoch.business.admin.model;

/**
 * 分享领奖配置表
 *
 * @author luliang_yu
 * @date 2018-11-27
 */

public class Share {

    private Integer id;

    private String itemId;

    private Integer delayTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }
}
