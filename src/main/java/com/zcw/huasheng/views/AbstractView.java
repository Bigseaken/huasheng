package com.zcw.huasheng.views;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-02 18:09
 */
public class AbstractView {

    protected JSONObject getResult(Object... o) {
        JSONObject result = new JSONObject();
        result.put("code", 100);
        if (o != null && o.length > 0)
            result.put("data", o[0]);
        return result;
    }

    protected JSONObject getErrResult() {
        JSONObject result = new JSONObject();
        result.put("code", 200);
        return result;
    }
}
