package com.zcw.huasheng.views;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-02 18:09
 */
public class AbstractView {

    protected JSONObject getResult(Object o){
        JSONObject result = new JSONObject();
        result.put("code",100);
        result.put("data",o);
        return result;
    }
}
