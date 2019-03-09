package com.zcw.huasheng;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-09 20:36
 */
@Mapper
public interface OrderDao {
    @Insert("INSERT into `order` (num,totalPrice,status,sessionId,date) VALUES(" +
            "#{order.num},#{order.totalPrice},#{order.status},#{order.sessionId},#{order.date})")
    @Options(useGeneratedKeys = true, keyProperty = "order.orderId")
    int addOrder(@Param("order") JSONObject params);

    @Insert("INSERT into order_detail  (goodId,amount,orderId) VALUES(" +
            "#{order.goodId},#{order.amount},#{order.orderId})")
    void addOrderDetail(@Param("order")JSONObject params);
}
