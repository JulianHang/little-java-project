package tech.zlia.interest.tools;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 * @version  2019-05-07
 * @author  zlia
 * @since  1.8
 */
@SuppressWarnings(value = "unused")
public final class TimeUtils {

    /**
     * 默认的时间格式
     */
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(TimeFormat.LONG_DATE_PATTERN_LINE);

    /**
     * 默认的日期格式
     */
    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(TimeFormat.SHORT_DATE_PATTERN_LINE);

    /**
     * 按指定格式解析字符串成日期
     * @param str 字符串
     * @param format 日期格式
     * @return 日期
     */
    public static LocalDate parseDate(String str, String format) {
        if(str == null || "".equals(str)) {
            return parseDate(str);
        } else {
            return LocalDate.parse(str, DateTimeFormatter.ofPattern(format));
        }
    }

    /**
     * 按指定格式解析字符串成日期
     * @param str 字符串，字符串的格式应该是yyyy-MM-dd 如2019-05-07
     * @return 日期
     */
    public static LocalDate parseDate(String str) {
        return LocalDate.parse(str, DEFAULT_DATE_FORMATTER);
    }

    /**
     * 日期转换成字符串
     * @param date 日期
     * @return 按照默认格式转换成字符串
     */
    public static String date2Str(LocalDate date) {
        return DEFAULT_DATE_FORMATTER.format(date);
    }

    /**
     * 日期转换成字符串
     * @param date 日期
     * @return 按照指定格式转换成字符串
     */
    public static String date2Str(LocalDate date, String format) {
        if(format == null || "".equals(format)) {
            return date2Str(date);
        } else {
            return DateTimeFormatter.ofPattern(format).format(date);
        }
    }

    /**
     * 按指定格式解析字符串成时间
     * @param str 字符串
     * @param format 时间格式
     * @return 时间
     */
    public static LocalDateTime parseTime(String str, String format) {
        if(format == null || "".equals(format)) {
            return parseTime(str);
        } else {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(format));
        }
    }

    /**
     * 按指定格式解析字符串成时间
     * @param str 字符串，字符串的格式应该是yyyy-MM-dd HH:mm:ss 如2019-05-07 12:00:00
     * @return 时间
     */
    public static LocalDateTime parseTime(String str) {
        return LocalDateTime.parse(str, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * 日期转换成字符串
     * @param time 时间
     * @return 按照默认格式转换成字符串
     */
    public static String time2Str(LocalDateTime time) {
        return DEFAULT_DATETIME_FORMATTER.format(time);
    }

    /**
     * 日期转换成字符串
     * @param time 时间
     * @return 按照指定格式转换成字符串
     */
    public static String time2Str(LocalDateTime time, String format) {
        if(format == null || "".equals(format)) {
            return time2Str(time);
        } else {
            return DateTimeFormatter.ofPattern(format).format(time);
        }
    }

    public class TimeFormat {

        /**
         * 日期格式
         */
        public static final String SHORT_DATE_PATTERN_LINE = "yyy-MM-dd";
        public static final String SHORT_DATE_PATTERN_SLASH = "yyyy/MM/dd";
        public static final String SHORT_DATE_PATTERN_DOUBLE_SLASH ="yyyy\\MM\\dd";
        public static final String SHORT_DATE_PATTERN_NONE = "yyyyMMdd";

        /**
         * 长时间格式
         */
        public static final String LONG_DATE_PATTERN_LINE = "yyyy-MM-dd HH:mm:ss";
        public static final String LONG_DATE_PATTERN_SLASH = "yyyy/MM/dd HH:mm:ss";
        public static final String LONG_DATE_PATTERN_DOUBLE_SLASH = "yyyy\\MM\\dd HH:mm:ss";
        public static final String LONG_DATE_PATTERN_NONE = "yyyyMMdd HH:mm:ss";

        /**
         * 长时间格式 带毫秒
         */
        public static final String LONG_DATE_PATTERN_WITH_MILSEC_LINE = "yyyy-MM-dd HH:mm:ss.SSS";
        public static final String LONG_DATE_PATTERN_WITH_MILSEC_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";
        public static final String LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH = "yyyy\\MM\\dd HH:mm:ss.SSS";
        public static final String LONG_DATE_PATTERN_WITH_MILSEC_NONE ="yyyyMMdd HH:mm:ss.SSS";

    }

}

