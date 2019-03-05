package com.zcw.huasheng.views;

import com.alibaba.fastjson.JSONObject;
import com.zcw.huasheng.dao.GoodsInfoDao;
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
public class HomeView extends AbstractView {
    @Autowired
    GoodsInfoDao goodsInfoDao;

    @GetMapping("getBanner")
    public JSONObject getBanner() {
        return getResult(goodsInfoDao.getBanner());
    }

    @GetMapping("getInformationById")
    public JSONObject getInformationById(Long id) {
        return getResult(goodsInfoDao.getInformationById(id));
    }

    @GetMapping("getHotGoods")
    public JSONObject getHotGoods() {
        return getResult(goodsInfoDao.getHotGoods());
    }

    @GetMapping("getChoice")
    public JSONObject getChoice() {
        return getResult(goodsInfoDao.getChoice());
    }

    @GetMapping("getSponsor")
    public JSONObject getSponsor() {
        return getResult(goodsInfoDao.getSponsor());
    }

    @GetMapping("getGoodById")
    public JSONObject getGoodById(Long id) {
        return getResult(goodsInfoDao.getGoodById(id));
    }

    @PostMapping("addCar")
     public JSONObject addCar(@RequestBody JSONObject good){
        String[] params = new String[]{"goodId","amount","sessionId"};
        for(String k :params){
            if(!good.containsKey(k))
                return getErrResult();
        }
//        int count = goodsInfoDao.isHaveGood(good);
        goodsInfoDao.addCar(good);
        return getResult();
    }
}
