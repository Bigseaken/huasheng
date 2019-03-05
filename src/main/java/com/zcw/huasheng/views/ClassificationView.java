package com.zcw.huasheng.views;

import com.alibaba.fastjson.JSONObject;
import com.zcw.huasheng.dao.SpeciesDao;
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
 * @create 2019-03-02 20:15
 */
@RestController
@CrossOrigin
@RequestMapping("class")
@Api(description="分类")
public class ClassificationView extends AbstractView {

    @Autowired
    SpeciesDao speciesDao;

    @ApiOperation("获取分类商品")
    @GetMapping("getSpecies")
    public JSONObject getSpecies(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kind",speciesDao.getKind());
        jsonObject.put("brand",speciesDao.getBrand());
        return getResult(jsonObject);
    }

}
