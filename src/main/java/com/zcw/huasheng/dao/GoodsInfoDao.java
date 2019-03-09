package com.zcw.huasheng.dao;

import com.alibaba.fastjson.JSONObject;
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
    @Select("SELECT * from goods_info where id = #{id}")
    JSONObject getGoodById(@Param("id")Long id);

    @Insert("INSERT into shop_car(goodId,amount,sessionId,status) " +
            "VALUES(#{good.goodId},#{good.amount},#{good.sessionId},1)")
    void addCar(@Param("good") JSONObject good);

    @Select("SELECT * from shop_car where goodId = #{good.goodId} and sessionId = #{good.sessionId}")
    JSONObject isHaveGood(@Param("good")JSONObject good);

    @Update("update shop_car set goodId = #{good.goodId} , sessionId = #{good.sessionId} ," +
            " amount = #{good.amount} where id= #{good.id}")
    void updateCar(@Param("good")JSONObject good);

    @Select("SELECT sum(amount) as total from shop_car where sessionId = #{sessionId}")
    JSONObject getCar(@Param("sessionId") Long sessionId);
    @Select("SELECT gi.id as goodId,sc.id,sc.amount,gi.`name`,gi.`describe`,gi.price,gi.img " +
            "FROM shop_car sc LEFT JOIN goods_info gi ON gi.id = sc.goodId " +
            "WHERE sessionId = #{sessionId}")
    List<JSONObject> getAllCar(@Param("sessionId")Long sessionId);
}
