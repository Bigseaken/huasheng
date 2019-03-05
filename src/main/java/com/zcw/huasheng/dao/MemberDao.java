package com.zcw.huasheng.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.*;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-05 22:40
 */
@Mapper
public interface MemberDao {
    @Insert("insert into member(mobile) value(#{member.mobile})")
    @Options(useGeneratedKeys = true, keyProperty = "member.id")
    int addMember(@Param("member") JSONObject member);

    @Select("select * from member where mobile = #{member.mobile}")
    JSONObject getMember(@Param("member") JSONObject member);

    @Select("select * from member where mobile = #{mobile}")
    JSONObject getMemberByMobile(@Param("mobile") String mobile);
}
