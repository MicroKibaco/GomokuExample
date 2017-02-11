package com.asiainfo.gomoku.utils;

import android.graphics.Point;

import java.util.List;

/**
 * 作者:小木箱 邮箱:yangzy3@asiainfo.com 创建时间:2017年02月10日22点13分 描述:
 */
public class GomokuCheckUtil {

    private static final int MAX_COUNT_IN_LINE = 5;

    /**
     * 描述:纵向检查是否五个棋连珠 创建时间:2/10/17/22:02 作者:小木箱 邮箱:yangzy3@asiainfo.com
     */
    public static boolean checkVertical(int x, int y, List<Point> points) {
        int count = 1;

        //上
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x, y + i))) {

                count++;

            } else {

                break;

            }

        }
        if (count == MAX_COUNT_IN_LINE)
            return true;
        //下
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x, y - i))) {

                count++;

            } else {

                break;

            }

        }

        return count == MAX_COUNT_IN_LINE;


    }

    /**
     * 描述:横向检查是否五个棋连珠 创建时间:2/10/17/22:01 作者:小木箱 邮箱:yangzy3@asiainfo.com
     */
    public static boolean checkHorizonal(int x, int y, List<Point> points) {

        int count = 1;

        //横向左
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x - i, y))) {

                count++;

            } else {

                break;

            }

        }
        if (count == MAX_COUNT_IN_LINE)
            return true;
        //横向右
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x, y - i))) {

                count++;

            } else {

                break;

            }

        }

        return count == MAX_COUNT_IN_LINE;


    }

    /**
     * 描述:左斜是否五个棋连珠 创建时间:2/10/17/22:01 作者:小木箱 邮箱:yangzy3@asiainfo.com
     */
    public static boolean checkLeftDiagonal(int x, int y, List<Point> points) {

        int count = 1;

        //横向左
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x - i, y + i))) {

                count++;

            } else {

                break;

            }

        }
        if (count == MAX_COUNT_IN_LINE)
            return true;
        //横向右
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x + i, y - i))) {

                count++;

            } else {

                break;

            }

        }

        return count == MAX_COUNT_IN_LINE;


    }

    /**
     * 描述:右斜是否五个棋连珠 创建时间:2/10/17/22:01 作者:小木箱 邮箱:yangzy3@asiainfo.com
     */
    public static boolean checkRightDiagonal(int x, int y, List<Point> points) {

        int count = 1;

        //横向左
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x - i, y - i))) {

                count++;

            } else {

                break;

            }

        }
        if (count == MAX_COUNT_IN_LINE)
            return true;
        //横向右
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {

            if (points.contains(new Point(x + i, y + i))) {

                count++;

            } else {

                break;

            }

        }

        return count == MAX_COUNT_IN_LINE;


    }
}
