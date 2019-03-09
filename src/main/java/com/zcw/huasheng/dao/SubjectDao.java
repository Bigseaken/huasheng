package com.zcw.huasheng.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-09 15:07
 */
@Mapper
public interface SubjectDao {

    @Select("SELECT hs.id,hs.`comment`,gi.`name`,gi.price,gi.img,m.mobile,m.portrait " +
            "FROM hs_subject hs " +
            "LEFT JOIN goods_info gi ON hs.goodId = gi.id " +
            "LEFT JOIN member m ON hs.memberId = m.id")
    List<JSONObject> getSubject();

}
