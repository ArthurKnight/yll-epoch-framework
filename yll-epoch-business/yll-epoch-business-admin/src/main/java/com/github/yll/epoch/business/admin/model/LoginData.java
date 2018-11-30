package com.github.yll.epoch.business.admin.model;

import java.io.Serializable;

/**
 * @author luliang_yu
 * @date 2018-11-27
 */

public class LoginData implements Serializable {


    public String encryptedData;
    public String code;
    public String iv;
    public String name;
    public String area;
    public Integer headFrame;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getHeadFrame() {
        return headFrame;
    }

    public void setHeadFrame(Integer headFrame) {
        this.headFrame = headFrame;
    }
}
