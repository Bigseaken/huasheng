package com.zcw.huasheng.views;

import com.alibaba.fastjson.JSONObject;
import com.zcw.huasheng.OrderDao;
import com.zcw.huasheng.dao.GoodsInfoDao;
import com.zcw.huasheng.utils.OrderNumTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author withqianqian@163.com
 * @create 2019-03-02 17:46
 */
@RestController
@CrossOrigin
@RequestMapping("home")
@Api(description = "首页相关")
public class HomeView extends AbstractView {
    @Autowired
    GoodsInfoDao goodsInfoDao;
    @Autowired
    OrderDao orderDao;

    @ApiOperation("获取banner")
    @GetMapping("getBanner")
    public JSONObject getBanner() {
        return getResult(goodsInfoDao.getBanner());
    }

    @ApiOperation(value = "根据id获取资讯", notes = "id=1")
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

    @ApiOperation(value = "根据商品idu获取商品详情", notes = "id=1")
    @GetMapping("getGoodById")
    public JSONObject getGoodById(Long id) {
        return getResult(goodsInfoDao.getGoodById(id));
    }

    @ApiOperation(value = "添加购物车", notes = "{goodId:商品id,amount:数量，sessionId:登陆用户id}")
    @PostMapping("addCar")
    public JSONObject addCar(@RequestBody JSONObject good) {
        String[] params = new String[]{"goodId", "amount", "sessionId"};
        for (String k : params) {
            if (!good.containsKey(k))
                return getErrResult("缺少参数"+k);
        }
        JSONObject existGood = goodsInfoDao.isHaveGood(good);
        if (existGood != null) {
            long amount = good.getIntValue("amount") + existGood.getIntValue("amount");
            good.put("amount", amount);
            good.put("id", existGood.getLong("id"));
            goodsInfoDao.updateCar(good);
        } else {
            goodsInfoDao.addCar(good);
        }
        return getResult();
    }

    @ApiOperation(value = "显示购物车数量",notes = "sessionId=1")
    @GetMapping("getCar")
    public JSONObject getCar(Long sessionId){
        return getResult(goodsInfoDao.getCar(sessionId));
    }

    @ApiOperation(value = "显示购物车信息",notes = "sessionId=1")
    @GetMapping("getAllCar")
    public JSONObject getAllCar(Long sessionId){
        return getResult(goodsInfoDao.getAllCar(sessionId));
    }

    @ApiOperation(value = "创建订单",notes = "直接购买：{\"goodId\":1,\"amount\":10,\"type\":1,\"sessionId\":52}" +
            "购物车下单{\"carIds\":[21,22,23],\"type\":2,\"sessionId\":52}")
    @PostMapping("addOrder")
    public JSONObject addOrder(@RequestBody JSONObject params) {
        if(!params.containsKey("type")||params.containsKey("sessionId"))
            return getErrResult("参数不正确");
        if(params.getIntValue("type") ==1){
            if(!params.containsKey("goodId"))
                return getErrResult("没有商品id");
        }else {
            if(!params.containsKey("carIds"))
                return getErrResult("没有购物车信息");
        }

        params.put("date",new Date());
        int type = (int) params.get("type");
        //直接购买
        if(type == 1){
            int amount = params.getIntValue("amount");
            JSONObject good = goodsInfoDao.getGoodById(params.getLong("goodId"));
            Integer price = 0;
            if (good != null)
                price = good.getInteger("price");
            params.put("num", OrderNumTool.getNum());
            params.put("status", 1);
            params.put("totalPrice", price * amount);
            orderDao.addOrder(params);

            //添加订单详情
            orderDao.addOrderDetail(params);
        //购物车购买
        }else {
            List<Integer> carIds = (List<Integer>) params.get("carIds");
            List<JSONObject> carList = new ArrayList<>();
            Integer totalPrice = 0;
            for(Integer carId:carIds){
                JSONObject carInfo = goodsInfoDao.getCarById(carId);
                if(carId!=null){
                    carList.add(carInfo);
                    int price = carInfo.getIntValue("price");
                    int amount  = carInfo.getIntValue("amount");
                    totalPrice += price*amount;
                    //逻辑删除删除购物车
                    goodsInfoDao.deleteCar(carInfo);
                }
            }
            params.put("status",1);
            params.put("num", OrderNumTool.getNum());
            params.put("totalPrice", totalPrice);
            orderDao.addOrder(params);

            for(JSONObject car :carList){
                JSONObject orderDetail = new JSONObject();
                orderDetail.put("orderId",params.getLong("orderId"));
                orderDetail.put("amount",car.getIntValue("amount"));
                orderDetail.put("goodId",car.getLongValue("goodId"));
                orderDao.addOrderDetail(orderDetail);

            }
        }
        return getResult();
    }

    @ApiOperation(value = "获取订单信息",notes = "status=1:待支付 2:代发货 3:已发货 不传查询全部订单 &sessionId=1")
    @GetMapping("getOrderList")
    public JSONObject getOrderList(@RequestParam(required = false,value = "")Integer status,
                                   Long sessionId){
        if(status == null)
            return getResult(goodsInfoDao.getOrderList(sessionId));
        else
            return getResult(goodsInfoDao.getOrderListByType(status,sessionId));
    }

}
