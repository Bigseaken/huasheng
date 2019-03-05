package com.zcw.huasheng.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
