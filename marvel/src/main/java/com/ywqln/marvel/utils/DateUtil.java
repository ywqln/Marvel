package com.ywqln.marvel.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 描述:字符串格式化工具.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public class DateUtil {
    public static final String CHINA_DATETIME_FORMAT = "yyyy年MM月dd日 HH:mm";
    public static final String LONG_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String SHORT_DATETIME_FORMAT = "MM-dd HH:mm";
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_DATE_FORMAT = "MM-dd";
    public static final String LONG_TIME_FORMAT = "HH:mm:sss";
    public static final String SHORT_TIME_FORMAT = "HH:mm";


    public static final String getTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
    }

    public static final String changeChinaDate(String date) {
        DateFormat ChinaDate = new SimpleDateFormat(CHINA_DATETIME_FORMAT, Locale.CHINA);
        new Date().setTime(Long.parseLong(date));
        return ChinaDate.format(date);
    }

    //格式化时间(年月日)
    public static final String formatDate(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_FORMAT, Locale.CHINA);
            return sdf.format(date);
        } catch (Exception e) {
            return StringUtil.Empty;
        }
    }

    //格式化时间(年月日 周)
    public static final String format2Week(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E", Locale.CHINA);
            return sdf.format(date);
        } catch (Exception e) {
            return StringUtil.Empty;
        }
    }

    public static long dateStingToLong(String str) {
        if (str != null) {
            Date date = null;
            String time = str;
            if (str.length() > 19) {
                time = time.substring(0, time.lastIndexOf("."));
            }
            time = time.substring(0, time.lastIndexOf("T")) + " " + time.substring(
                    time.lastIndexOf("T") + 1);
            SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATETIME_FORMAT);
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date.getTime();
        }
        return 0;
    }
}
