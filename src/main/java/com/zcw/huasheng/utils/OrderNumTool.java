package com.zcw.huasheng.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-05 23:00
 */
public class OrderNumTool {
    final static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    public static String getNum(){
        return format.format(new Date());
    }
}
