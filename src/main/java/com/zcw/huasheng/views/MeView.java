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
@Api(description = "我的")
public class MeView extends AbstractView {

    @Autowired
    MemberDao memberDao;

    @ApiOperation(value = "注册用户", notes = "{mobile:手机}")
    @PostMapping("addMember")
    public JSONObject addMember(@RequestBody JSONObject member) {
        if (memberDao.getMember(member) != null)
            return getErrResult("手机号码：" + member.getString("mobile") + " 已注册了！");
        memberDao.addMember(member);
        return getResult(member);
    }

    @ApiOperation(value = "根据手机号码获取用户", notes = "mobile=1111")
    @GetMapping("getMember")
    public JSONObject getMember(String mobile, @RequestParam(required = false, defaultValue = "") String password) {
        if (password == "") {
            JSONObject params = new JSONObject();
            params.put("mobile", mobile);
            params.put("password", password);
            if(memberDao.getMember(params)!=null)
                return getResult(memberDao.getMember(params));
            else
                return  getErrResult("密码错误！");
        }
        return getResult(memberDao.getMemberByMobile(mobile));
    }
}
