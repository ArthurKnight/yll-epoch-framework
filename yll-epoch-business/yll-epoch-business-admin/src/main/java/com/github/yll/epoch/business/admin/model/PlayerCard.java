package com.github.yll.epoch.business.admin.model;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author luliang_yu
 * @date 2018-11-23
 */

public class PlayerCard implements Serializable {

    private Integer id;

    private Integer playerId;

    private Integer cardId;

    private Integer cardOrder;

    private Date getTime;

    private Integer cardPng;

    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCardPng() {
        return cardPng;
    }

    public void setCardPng(Integer cardPng) {
        this.cardPng = cardPng;
    }

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

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCardOrder() {
        return cardOrder;
    }

    public void setCardOrder(Integer cardOrder) {
        this.cardOrder = cardOrder;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlayerCard.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("playerId=" + playerId)
                .add("cardId=" + cardId)
                .add("cardOrder=" + cardOrder)
                .add("getTime=" + getTime)
                .toString();
    }
}
