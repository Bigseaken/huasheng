package com.zcw.huasheng.views;

import com.alibaba.fastjson.JSONObject;
import com.zcw.huasheng.dao.GoodsInfoDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-02 17:46
 */
@RestController
@CrossOrigin
@RequestMapping("home")
@Api("首页相关")
public class HomeView extends AbstractView {
    @Autowired
    GoodsInfoDao goodsInfoDao;

    @ApiOperation("获取banner")
    @GetMapping("getBanner")
    public JSONObject getBanner() {
        return getResult(goodsInfoDao.getBanner());
    }

    @ApiOperation(value = "根据id获取资讯",notes = "id=1")
    @GetMapping("getInformationById")
    public JSONObject getInformationById(Long id) {
        return getResult(goodsInfoDao.getInformationById(id));
    }

    @ApiOperation("获取热门商品")
    @GetMapping("getHotGoods")
    public JSONObject getHotGoods() {
        return getResult(goodsInfoDao.getHotGoods());
    }

    @ApiOperation("获取人气商品")
    @GetMapping("getChoice")
    public JSONObject getChoice() {
        return getResult(goodsInfoDao.getChoice());
    }

    @ApiOperation("获取专题")
    @GetMapping("getSponsor")
    public JSONObject getSponsor() {
        return getResult(goodsInfoDao.getSponsor());
    }

    @ApiOperation(value = "根据商品idu获取商品详情",notes = "id=1")
    @GetMapping("getGoodById")
    public JSONObject getGoodById(Long id) {
        return getResult(goodsInfoDao.getGoodById(id));
    }

    @ApiOperation(value = "添加购物车" ,notes = "{goodId:商品id,amount:数量，sessionId:登陆用户id}")
    @PostMapping("addCar")
    public JSONObject addCar(@RequestBody JSONObject good) {
        String[] params = new String[]{"goodId", "amount", "sessionId"};
        for (String k : params) {
            if (!good.containsKey(k))
                return getErrResult();
        }
//        int count = goodsInfoDao.isHaveGood(good);
        goodsInfoDao.addCar(good);
        return getResult();
    }
}
