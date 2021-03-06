package com.github.yll.epoch.business.admin.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author luliang_yu
 * @date 2018-11-07
 */

public class Pet implements Serializable {

    @NotNull(message = "id不能为空")
    private int id;
    private String name;

    public Pet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
