package com.ywqln.marvel.utils;

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

    /**
     * 空格
     */
    public static final String Space = " ";

    /**
     * 检查字符串，如果是null对象就赋值为空字符串{@link Empty}
     *
     * @param value 被检查字符串
     * @return 如果是不是null对象，就返回传入内容
     */
    public static final String nullToEmpty(String value) {
        if (value == null) {
            value = Empty;
        }
        return value;
    }

    /**
     * 检查字符串是不是null或者空字符串{@link Empty}
     *
     * @param value 被检查字符串
     * @return 检查结果
     */
    public static final boolean isNullOrEmpty(String value) {
        if (null == value) return true;
        if (value.equals(Empty)) return true;
        return false;
    }
}
