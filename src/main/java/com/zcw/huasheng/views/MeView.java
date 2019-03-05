package com.zcw.huasheng.views;

import com.alibaba.fastjson.JSONObject;
import com.zcw.huasheng.dao.MemberDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-05 22:38
 */
@CrossOrigin
@RestController
@RequestMapping("me")
@Api("我的")
public class MeView extends AbstractView {

    @Autowired
    MemberDao memberDao;

    @ApiOperation(value = "注册用户",notes = "{mobile:手机}")
    @PostMapping("addMember")
    public JSONObject addMember(@RequestBody JSONObject member) {
        if(memberDao.getMember(member) !=null)
            return getErrResult();
        memberDao.addMember(member);
        return getResult(member);
    }
    @ApiOperation(value = "根据手机号码获取用户",notes = "mobile=1111")
    @GetMapping("getMember")
    public JSONObject getMember(String mobile){
        return getResult(memberDao.getMemberByMobile(mobile));
    }
}
