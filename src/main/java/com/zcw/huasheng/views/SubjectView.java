package com.zcw.huasheng.views;

import com.alibaba.fastjson.JSONObject;
import com.zcw.huasheng.dao.SubjectDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-09 15:06
 */
@CrossOrigin
@RestController
@RequestMapping("subject")
@Api(description = "专题")
public class SubjectView extends AbstractView{

    @Autowired
    SubjectDao subjectDao;

    @ApiOperation(value = "显示专题列表")
    @GetMapping("list")
    public JSONObject getSubject(){
        return getResult(subjectDao.getSubject());
    }
}
