package com.mysiteforme.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.mysiteforme.admin.base.MySysUser;
import com.mysiteforme.admin.entity.*;
import com.mysiteforme.admin.service.*;
import com.mysiteforme.admin.util.OrderUtils;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.mysiteforme.admin.annotation.SysLog;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单  前端控制器
 * </p>
 *
 * @author jing
 * @since 2023-02-02
 */
@Controller
@RequestMapping("/veorder")
public class VeorderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VeorderController.class);

    @Autowired
    private VeorderService veorderService;

    @Autowired
    private VegetableSellService vegetableSellService;
    
    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategorySecService categorySecService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private VegetableService vegetableService;

    @GetMapping("list")
    @SysLog("跳转订单列表")
    public String list(){
        return "/admin/veorder/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求订单列表数据")
    public LayerData<Veorder> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Veorder> layerData = new LayerData<>();
        EntityWrapper<Veorder> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String orderno = (String) map.get("orderno");
            if(StringUtils.isNotBlank(orderno)) {
                wrapper.like("orderno",orderno);
            }else{
                map.remove("orderno");
            }

            String express = (String) map.get("express");
            if(StringUtils.isNotBlank(express)) {
                wrapper.like("express",express);
            }else{
                map.remove("express");
            }

            String cname = (String) map.get("cname");
            if(StringUtils.isNotBlank(cname)) {
                wrapper.like("cname",cname);
            }else{
                map.remove("cname");
            }

        }
        Page<Veorder> pageData = veorderService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增订单页面")
    public String add(){
        return "/admin/veorder/add";
    }

    @PostMapping("add")
    @SysLog("保存新增订单数据")
    @ResponseBody
    public RestResponse addVorder(){
        // 获取当前登录用户
        User user = userService.findUserById(MySysUser.id());
        // 获取购物车数据
        EntityWrapper<Cart> wrapper = new EntityWrapper<>();
        wrapper.eq("customer_id",String.valueOf(MySysUser.id()));
        List<Cart> carts = cartService.selectList(wrapper);
        for (Cart c: carts) {
            Veorder vorder = new Veorder();
            vorder.setCname(user.getNickName());
            vorder.setOrderno(OrderUtils.getCode(MySysUser.id().intValue()));
            vorder.setExpress(OrderUtils.toCode(MySysUser.id().intValue()));
            vorder.setVname(c.getVname());
            vorder.setVid(c.getVid());
            vorder.setVimg(c.getVimg());
            vorder.setOstatus("待发货");
            vorder.setVprice(c.getVprice());
            vorder.setVcout(c.getVcount());
            vorder.setOprice(c.getVprice().multiply(new BigDecimal(c.getVcount())));
            vorder.setCtel(user.getTel());
            veorderService.insert(vorder);

            //统计浏览次数
            EntityWrapper<VegetableSell> wr = new EntityWrapper<>();
            wr.eq("vid",c.getVid());
            VegetableSell v = vegetableSellService.selectList(wr).get(0);
            v.setBuyCount(v.getBuyCount()+1);
            vegetableSellService.updateById(v);
            //删除购物车
            cartService.deleteById(c.getId());
        }

        // 添加订单
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑订单页面")
    public String edit(Long id,Model model){
        Veorder veorder = veorderService.selectById(id);
        model.addAttribute("veorder",veorder);
        return "/admin/veorder/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑订单数据")
    public RestResponse edit(Veorder veorder){
        if(null == veorder.getId() || 0 == veorder.getId()){
            return RestResponse.failure("ID不能为空");
        }
        veorderService.updateById(veorder);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除订单数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Veorder veorder = veorderService.selectById(id);
        veorder.setDelFlag(true);
        veorderService.updateById(veorder);
        return RestResponse.success();
    }

    @GetMapping("getOrder")
    public String getOrder(Model model,HttpServletRequest request){
        // 分类
        category(model);

        // 购物车
        //购物车
        User currentUser = (User) request.getAttribute("currentUser");
        EntityWrapper<Cart> w = new EntityWrapper<>();
        w.eq("customer_id",currentUser.getId());
        List<Cart> carts = cartService.selectList(w);
        int sum = carts.stream().mapToInt(x -> (Integer.valueOf(x.getVcount())) * x.getVprice().intValue()).sum();
        model.addAttribute("carts",carts);
        model.addAttribute("sum",sum);

        // 订单
        User user = userService.findUserById(MySysUser.id());
        EntityWrapper<Veorder> wrapper = new EntityWrapper<>();
        wrapper.eq("ctel",user.getTel());
        List<Veorder> veorders = veorderService.selectList(wrapper);
        model.addAttribute("veorders",veorders);
        return "/shop/my-account";
    }

    //类目集合
    public void category(Model model){
        //类别集合
        JSONArray array = new JSONArray();
        List<Category> categoryList = categoryService.selectList(new EntityWrapper<>());
        for (int i = 0; i < categoryList.size(); i++) {
            Category category = categoryList.get(i);
            JSONObject object = new JSONObject();
            object.put("name",category.getName());
            object.put("id",category.getId());

            Wrapper<CategorySec> w = new EntityWrapper<>();
            w.eq("cid",category.getId());
            List<CategorySec> categorySecs = categorySecService.selectList(w);
            if(CollectionUtils.isEmpty(categoryList)){
                object.put("categorySecs",null);
            }else {
                object.put("categorySecs",categorySecs);
            }
            array.add(object);
        }
        model.addAttribute("categoryList",array);
        LOGGER.info("array:{}", JSON.toJSONString(array));
    }

}