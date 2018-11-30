package com.github.yll.epoch.business.admin.vo;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author luliang_yu
 * @date 2018-11-26
 */

public class LevelRecordVo implements Serializable {

    private Integer productId;

    private Integer stars;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LevelRecordVo.class.getSimpleName() + "[", "]")
                .add("productId=" + productId)
                .add("stars=" + stars)
                .toString();
    }
}
