package com.witfrog.gtse.util;

import com.blankj.utilcode.util.SPUtils;

public class SPManager {

    private static final SPUtils SP_UTILS = SPUtils.getInstance("frog");

    public static String getId() {
        return SP_UTILS.getString("id", "");
    }

    public static void setId(String id) {
        SP_UTILS.put("id", id);
    }

    public static String getPhone() {
        return SP_UTILS.getString("phone", "");
    }

    public static void setPhone(String phone) {
        SP_UTILS.put("phone", phone);
    }

    public static String getAvatar() {
        return SP_UTILS.getString("avatar", "");
    }

    public static void setAvatar(String avatar) {
        SP_UTILS.put("avatar", avatar);
    }

    public static void clear() {
        SP_UTILS.clear();
    }

    public static void clearUser() {
        setId("");
        setPhone("");
        setAvatar("");
    }

}
