package com.zcw.huasheng.entity;

import netscape.javascript.JSObject;

import java.util.List;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-09 21:43
 */
public class One2Many {

    private Long id;

    private String num;

    private Long totalPrice;

    private Integer status;

    private List<JSObject> list;

    public List<JSObject> getList() {
        return list;
    }

    public void setList(List<JSObject> list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
