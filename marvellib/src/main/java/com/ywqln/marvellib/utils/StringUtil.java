package com.ywqln.marvellib.utils;

/**
 * 描述:字符串帮助工具.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public class StringUtil {
    /**
     * 空字符串,为避免不必要的创建空字符串对象.
     */
    public static final String Empty = "";
    public static final String Space = " "; // 空格

    public static final String nullToEmpty(String value) {
        if (value == null) {
            value = Empty;
        }
        return value;
    }
}
