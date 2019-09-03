package com.thoughtworks.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期转换工具类.
 */
@Slf4j
public class DateUtil {

    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT2 = "yyyyMMddHHmmss";
    public static final String FORMAT3 = "yyyy-MM-dd";
    public static final String FORMAT4 = "yyyyMMdd";
    public static final String FORMAT5 = "yyyy/MM/dd";
    public static final String FORMAT6 = "HH:mm:ss";
    public static final String FORMAT8 = "yyyy";
    public static final String FORMAT7 = "yyyy-MM";
    public static final String ZERO_OCLOCK = " 00:00:00";
    public static final String TWENTY_FOUR_OCLOCK = " 23:59:59";

    /**
     * 以指定等日期格式转换日期类型为字符串.
     * @param date 要转换的日期
     * @param format 日期格式样式
     * @return 转换结果
     */
    public static String formatDate(Date date, String format) {
        String formatDate;
        if (StringUtils.isNotBlank(format)) {
            formatDate = DateFormatUtils.format(date, format);
        } else {
            formatDate = DateFormatUtils.format(date, FORMAT3);
        }

        return formatDate;
    }

    /**
     * 获取报表统计日期的开始日期（精确到时分秒.
     * @param date 要转换的参数
     * @return 结果
     */
    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT1);

            try {
                date = sdf.parse(formatDate(date, DateUtil.FORMAT3) + DateUtil.ZERO_OCLOCK);
            } catch (ParseException exception) {
                log.error("function getDateStart failed, date = {}, exception is = {}", date, exception);
            }

            return date;
        }
    }

    /**
     * 获取报表统计日期的结束日期（精确到时分秒）.
     * @param date 要转换的参数
     * @return 结果
     */
    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;

        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT1);

            try {
                date = sdf.parse(formatDate(date, DateUtil.FORMAT3) + DateUtil.TWENTY_FOUR_OCLOCK);
            } catch (ParseException exception) {
                log.error("function getDateEnd failed, date = {}, exception is = {}", date, exception);
            }

            return date;
        }
    }

    /**
     * 获取报表统计日期的开始日期（精确到时分秒）.
     * @param date 要转换的参数
     * @return 结果
     */
    public static String getDateTimeStart(String date) {
        if (date == null) {
            return null;
        }
        return date + DateUtil.ZERO_OCLOCK;
    }

    /**
     * 获取报表统计日期的结束日期（精确到时分秒）.
     * @param date 要转换的参数
     * @return 结果
     */
    public static String getDateTimeEnd(String date) {
        if (date == null) {
            return null;
        }
        return date + DateUtil.TWENTY_FOUR_OCLOCK;
    }

    /**
     * 计算指定间隔区间的日期.
     * @param date 计算间隔的基准日期
     * @param day 间隔天数
     * @return 结果
     */
    public static Date calcDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 将字符串按照指定的格式转换成日期类型.
     * @param dateString 日期类型字符串
     * @param dateFormat 日期转换格式
     * @return 转换后结果
     */
    public static Date parseDate(String dateString, String dateFormat) {
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            date = sdf.parse(dateString);
        } catch (ParseException exception) {
            log.error("parse date failed , source string is {}, format is {}, exception is {}", dateString, dateFormat, exception);
            return null;
        }
        return date;
    }
}
