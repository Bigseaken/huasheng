package com.zcw.huasheng.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-02 20:21
 */
@Mapper
public interface SpeciesDao {

    @Select("SELECT name,id ,type from species GROUP BY name")
    List<JSONObject> getKind();

    @Select("SELECT * from species")
    List<JSONObject> getBrand();
}
