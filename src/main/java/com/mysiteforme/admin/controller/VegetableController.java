package com.mysiteforme.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.entity.*;
import com.mysiteforme.admin.service.*;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 蔬菜  前端控制器
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
@Controller
@RequestMapping("/vegetable")
public class VegetableController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VegetableController.class);

    @Autowired
    private VegetableService vegetableService;

    @Autowired
    private VegetableSellService vegetableSellService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CategorySecService categorySecService;

    @GetMapping("search")
    @SysLog("跳转蔬菜搜索列表")
    public String list(HttpServletRequest request,Model model,Vegetable vegetable,String price,
        @RequestParam(value = "page",defaultValue = "1")Integer page,
        @RequestParam(value = "limit",defaultValue = "10")Integer limit
    ){
        // 模糊查询蔬菜
        EntityWrapper<Vegetable> wrapper = new EntityWrapper<>();
        wrapper.like("name",vegetable.getName());
        if(StringUtils.isNotEmpty(price)&& !price.contains("￥")){
            String[] split = price.split("-");
            wrapper.gt("current_price",new BigDecimal(split[0]));
            wrapper.lt("current_price",new BigDecimal(split[1]));
        }
        if(StringUtils.isNotEmpty(vegetable.getCategoryFirst())){
            wrapper.eq("category_first",vegetable.getCategoryFirst());
        }
        if(StringUtils.isNotEmpty(vegetable.getCategorySecond())){
            wrapper.eq("category_second",vegetable.getCategorySecond());
        }
        Page<Vegetable> vegetablePage = vegetableService.selectPage(new Page<>(page, limit), wrapper);
        model.addAttribute("vegetables",vegetablePage.getRecords());
        model.addAttribute("counts",vegetablePage.getTotal());
        category(model);

        //购物车
        User currentUser = (User) request.getAttribute("currentUser");
        EntityWrapper<Cart> w = new EntityWrapper<>();
        w.eq("customer_id",currentUser.getId());
        List<Cart> carts = cartService.selectList(w);
        int sum = carts.stream().mapToInt(x -> (Integer.valueOf(x.getVcount())) * x.getVprice().intValue()).sum();
        model.addAttribute("carts",carts);
        model.addAttribute("sum",sum);
        model.addAttribute("page",page);

        // 标签
        List<String> tagList = vegetableService.findTagList();
        model.addAttribute("tags",tagList);
        return "/shop/shop-right-sidebar-search";
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


    @GetMapping("detail")
    @SysLog("跳转蔬菜详情")
    public String detail(HttpServletRequest request,Model model, String id){
        Vegetable vegetable = vegetableService.selectById(id);

        //统计浏览次数
        VegetableSell v = null;
        EntityWrapper<VegetableSell> wr = new EntityWrapper<>();
        wr.eq("vid",id);
        if(vegetableSellService.selectList(wr).size() == 0){
            v = new VegetableSell();
            v.setVid(id);
            v.setVname(vegetable.getName());
            v.setViewCount(1);
            v.setBuyCount(0);
            vegetableSellService.insert(v);
        }else {
            v = vegetableSellService.selectList(wr).get(0);
            v.setViewCount(v.getViewCount()+1);
            vegetableSellService.updateById(v);
        }
        category(model);

        //购物车
        User currentUser = (User) request.getAttribute("currentUser");
        EntityWrapper<Cart> w = new EntityWrapper<>();
        w.eq("customer_id",currentUser.getId());
        List<Cart> carts = cartService.selectList(w);
        int sum = carts.stream().mapToInt(x -> (Integer.valueOf(x.getVcount())) * x.getVprice().intValue()).sum();
        model.addAttribute("carts",carts);
        model.addAttribute("sum",sum);
        model.addAttribute("vegetable",vegetable);
        return "/shop/product-details";
    }

}