package com.zcw.huasheng.dao;

import com.alibaba.fastjson.JSONObject;
import com.zcw.huasheng.entity.GoodOne2Many;
import com.zcw.huasheng.entity.One2Many;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-02 18:05
 */
@Mapper
public interface GoodsInfoDao {

    @Select("SELECT * from banner where type=1")
    List<JSONObject> getBanner();

    @Select("SELECT * from banner where id = #{id}")
    JSONObject getInformationById(@Param("id") Long id);

    @Select("SELECT * from goods_info LIMIT 0,3")
    List<JSONObject> getHotGoods();

    @Select("SELECT * from goods_info LIMIT 0,10")
    List<JSONObject> getChoice();

    @Select("SELECT * from banner where type=2 LIMIT 1")
    JSONObject getSponsor();


    @Select("SELECT * from category where goodId = #{goodId}")
    JSONObject getCategory(@Param("goodId")Long goodId);

    @Select("SELECT * from goods_info where id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "describe", property = "describe"),
            @Result(column = "price", property = "price"),
            @Result(column = "img", property = "img"),
            @Result(column = "type", property = "type"),
            @Result(property = "categorys", column = "id"
                    , many = @Many(select = "com.zcw.huasheng.dao.GoodsInfoDao.getCategory"))
    })
    GoodOne2Many getGoodById(@Param("id")Long id);

    @Insert("INSERT into shop_car(goodId,amount,sessionId,status) " +
            "VALUES(#{good.goodId},#{good.amount},#{good.sessionId},1)")
    void addCar(@Param("good") JSONObject good);

    @Select("SELECT * from shop_car where goodId = #{good.goodId} and sessionId = #{good.sessionId} and status = 1")
    JSONObject isHaveGood(@Param("good")JSONObject good);

    @Update("update shop_car set goodId = #{good.goodId} , sessionId = #{good.sessionId} ," +
            " amount = #{good.amount} where id= #{good.id}")
    void updateCar(@Param("good")JSONObject good);

    @Select("SELECT sum(amount) as total from shop_car where sessionId = #{sessionId} and status = 1")
    JSONObject getCar(@Param("sessionId") Long sessionId);
    @Select("SELECT gi.id as goodId,sc.id,sc.amount,gi.`name`,gi.`describe`,gi.price,gi.img " +
            "FROM shop_car sc LEFT JOIN goods_info gi ON gi.id = sc.goodId " +
            "WHERE sessionId = #{sessionId} and status = 1 ")
    List<JSONObject> getAllCar(@Param("sessionId")Long sessionId);

    @Select("SELECT sc.amount,gi.price,gi.id as goodId,sc.id from shop_car sc " +
            "LEFT JOIN goods_info gi on sc.goodId = gi.id where sc.id = #{carId} and sc.status = 1")
    JSONObject getCarById(@Param("carId") Integer carId);

    @Update("update shop_car set status = 0 where id= #{car.id}")
    void deleteCar(@Param("car") JSONObject carInfo);

    @Select("SELECT gi.name,gi.img,od.amount,gi.price,gi.describe " +
            "from  order_detail od LEFT JOIN goods_info gi on od.goodId = gi.id " +
            "where od.orderId = #{id}")
    List<JSONObject> orderDetail(@Param("id") Long id);


    @Select("SELECT o.id,o.num,o.totalPrice,o.status ,o.date from `order` o where o.status = #{status} " +
            "and  o.sessionId=#{sessionId}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "num",property = "num"),
            @Result(column = "status",property = "status"),
            @Result(column = "totalPrice",property = "totalPrice"),
            @Result(column = "date",property = "date"),
            @Result(property = "list",column = "id",
            many=@Many(select = "com.zcw.huasheng.dao.GoodsInfoDao.orderDetail")),
    })
    List<One2Many> getOrderListByType(@Param("status") Integer status,@Param("sessionId") Long sessionId);

    @Select("SELECT o.id,o.num,o.totalPrice ,o.status ,o.date from `order` o where o.sessionId=#{sessionId}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "num",property = "num"),
            @Result(column = "status",property = "status"),
            @Result(column = "totalPrice",property = "totalPrice"),
            @Result(column = "date",property = "date"),
            @Result(property = "list",column = "id",
                    many=@Many(select = "com.zcw.huasheng.dao.GoodsInfoDao.orderDetail")),
    })
    List<JSONObject> getOrderList(@Param("sessionId") Long sessionId);
}
