package com.example.quickdevelop.component.util;

import java.util.UUID;

/**
 *<p>Title: UuidUtil</p>
 *<p>Description:主键 32位uuid </p>
 */
public class UuidUtil{

    public static String create1(){
        UUID uuid = UUID.randomUUID();
        return Long.toHexString(uuid.getMostSignificantBits()) + Long.toHexString(uuid.getLeastSignificantBits());
    }

    public static String create2(){
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24);
    }

    public static String create3(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }


}
