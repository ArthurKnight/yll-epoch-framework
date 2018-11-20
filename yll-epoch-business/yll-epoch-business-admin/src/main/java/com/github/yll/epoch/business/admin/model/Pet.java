package com.github.yll.epoch.business.admin.model;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author luliang_yu
 * @date 2018-11-07
 */

public class Pet implements Serializable {

    private int id;
    private String name;

    public Pet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pet.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
