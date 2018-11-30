package com.github.yll.epoch.business.admin.model;

import java.io.Serializable;

/**
 * @author luliang_yu
 * @date 2018-11-27
 */

public class Card implements Serializable {

    private Integer id;

    private Integer workId;

    private Integer cardPng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getCardPng() {
        return cardPng;
    }

    public void setCardPng(Integer cardPng) {
        this.cardPng = cardPng;
    }
}
