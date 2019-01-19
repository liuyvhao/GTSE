package com.witfrog.gtse.util;

import com.blankj.utilcode.util.StringUtils;

public class FormatUtil {

    public static String invisiblePhone(String s) {
        if (!StringUtils.isEmpty(s)) {
            if (s.length() == 11) {
                String left = s.substring(0, 3);
                String right = s.substring(7, 11);
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    builder.append("*");
                }
                return left + builder.toString() + right;
            } else {
                return s;
            }
        } else {
            return "";
        }
    }

    public static String invisibleIDCard(String s) {
        if (!StringUtils.isEmpty(s)) {
            if (s.length() > 14) {
                String left = s.substring(0, 6);
                String right = s.substring(14, s.length());
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 8; i++) {
                    builder.append("*");
                }
                return left + builder.toString() + right;
            } else {
                return s;
            }
        } else {
            return "";
        }
    }

    public static String invisibleString(String s) {
        if (!StringUtils.isEmpty(s)) {
            if (s.length() > 20) {
                String left = s.substring(0, 10);
                String right = s.substring(s.length() - 10, s.length());
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < s.length() - 20; i++) {
                    builder.append("*");
                }
                return left + builder.toString() + right;
            } else {
                return s;
            }
        } else {
            return "";
        }
    }
}
