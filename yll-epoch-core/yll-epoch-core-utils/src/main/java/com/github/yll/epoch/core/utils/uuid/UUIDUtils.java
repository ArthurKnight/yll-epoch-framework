package com.github.yll.epoch.core.utils.uuid;

import java.util.UUID;

/**
 * @author luliang_yu
 * @date 2018-11-20
 */

public class UUIDUtils {

    /**
     * 随机一个uuid（例如：6730ec011be14b4da76fbcf43edb5ab7）
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtils.getUUID());
    }
}
