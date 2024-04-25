package com.mlv.learn.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 运营中心统计一些时间的返回
 *
 * @author :ljs
 * @date : 2022/8/17
 */
public class OperatCenterTimeUtil {
    /**
     * 获取时间
     *
     * @return
     */
    public static List<String> getHours() {
        List<String> list = new ArrayList<>();
        String hours[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20",
                "21",
                "22",
                "23"
        };
        list = Arrays.asList(hours);
        return list;
    }

    /**
     * 获取本月的全部日期
     */
    public static List<String> getMonthDay() {
        List<String> monthDayList = new ArrayList<>();
        //本月最后一天
        DateTime dateTime = DateUtil.endOfMonth(new Date());
        String endDateTimeStr = DateUtil.format(dateTime, "YYYY-MM-dd");
        //获取最后一个字符就是天
        String day = endDateTimeStr.substring(endDateTimeStr.lastIndexOf("-") + 1);
        //天前面的
        for (int i = 1; i <= Integer.valueOf(day); i++) {
            String startStr = endDateTimeStr.substring(0, endDateTimeStr.lastIndexOf("-") + 1);
            if (i < 10) {
                startStr += "0" + i;
            } else {
                startStr += i;
            }


            monthDayList.add(startStr);
        }
        return monthDayList;
    }

    /**
     * 获取本周天数
     */
    public static List<String> getWeekDay() {
        List<String> weekDayList = new ArrayList<>();
        //本月最后一天
        String weeks[] = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        List<String> weekList = Arrays.asList(weeks);
        DateTime beginWeekDay = DateUtil.beginOfWeek(new Date());
        String beginWeekDayStr = DateUtil.format(beginWeekDay, "YYYY-MM-dd");
        //获取最后一个字符就是天
        String beginDay = beginWeekDayStr.substring(beginWeekDayStr.lastIndexOf("-") + 1);
        //查询本周的最后一天
        DateTime lastWeekDay = DateUtil.endOfWeek(new Date());
        String lastWeekDayStr = DateUtil.format(lastWeekDay, "YYYY-MM-dd");
        //获取最后一个字符就是天
        String endDay = lastWeekDayStr.substring(lastWeekDayStr.lastIndexOf("-") + 1);
        //天前面的
        for (int i = Integer.valueOf(beginDay); i <= Integer.valueOf(endDay); i++) {
            String startStr = beginWeekDayStr.substring(0, beginWeekDayStr.lastIndexOf("-") + 1);
            startStr += i;
            weekDayList.add(startStr);
        }
        //加后缀
      /*  List<String> finalList = new ArrayList<>();
        for (int i = 0; i < weekDayList.size(); i++) {
            String day = weekDayList.get(i) + "(" + weekList.get(i) + ")";
            finalList.add(day);
        }*/
        return weekDayList;
    }

    /**
     * 获取本年的所有月份
     *
     * @param
     */
    public static List<String> getYearMonth() {
        List<String> yearMonthList = new ArrayList<>();
        //查询本周的最后一天
        DateTime lastYearMonth = DateUtil.endOfYear(new Date());
        String lastYearMonthStr = DateUtil.format(lastYearMonth, "YYYY-MM");
        //天前面的
        for (int i = 1; i <= 12; i++) {
            String startStr = lastYearMonthStr.substring(0, lastYearMonthStr.lastIndexOf("-") + 1);
            if (i < 10) {
                startStr += "0" + i;
            } else {
                startStr += i;
            }

            yearMonthList.add(startStr);
        }
        return yearMonthList;
    }

    /**
     * 获取去年的所有月份
     *
     * @param
     */
    public static List<String> getLastYearMonth() {
        List<String> yearMonthList = new ArrayList<>();
        //查询本周的最后一天
        DateTime lastYearMonth = DateUtil.endOfYear(new Date());
        String lastYearMonthStr = DateUtil.format(lastYearMonth, "YYYY-MM");
        String year = lastYearMonthStr.substring(0, lastYearMonthStr.lastIndexOf("-"));
        //获取年
        //天前面的
        for (int i = 1; i <= 12; i++) {
            int lastYear = Integer.valueOf(year) - 1;
            String startStr = String.valueOf(lastYear);
            if (i < 10) {
                startStr += "-" + "0" + i;
            } else {
                startStr += "-" + i;
            }

            yearMonthList.add(startStr);
        }
        return yearMonthList;
    }
}


