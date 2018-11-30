package com.github.yll.epoch.business.admin.model;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * 玩家信息
 *
 * @author luliang_yu
 * @date 2018/11/26
 */
public class Player implements Serializable {

    private Integer id;

    private String ukId;

    private String area;

    private String name;

    private Integer star;

    private Integer sadou;

    private String head;

    private Integer headFrame;

    private Date createTimestamp;

    private Date lastChangeTimestamp;

    private String token;

    private Integer bg;

    private Integer loginType;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getBg() {
        return bg;
    }

    public void setBg(Integer bg) {
        this.bg = bg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUkId() {
        return ukId;
    }

    public void setUkId(String ukId) {
        this.ukId = ukId == null ? null : ukId.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getSadou() {
        return sadou;
    }

    public void setSadou(Integer sadou) {
        this.sadou = sadou;
    }

    public Integer getHeadFrame() {
        return headFrame;
    }

    public void setHeadFrame(Integer headFrame) {
        this.headFrame = headFrame;
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
        return new StringJoiner(", ", Player.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("ukId='" + ukId + "'")
                .add("area='" + area + "'")
                .add("name='" + name + "'")
                .add("star=" + star)
                .add("sadou=" + sadou)
                .add("headFrame=" + headFrame)
                .add("createTimestamp=" + createTimestamp)
                .add("lastChangeTimestamp=" + lastChangeTimestamp)
                .add("token='" + token + "'")
                .add("bg=" + bg)
                .add("loginType=" + loginType)
                .toString();
    }
}