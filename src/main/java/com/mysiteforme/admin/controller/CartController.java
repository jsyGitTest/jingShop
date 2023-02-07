package com.mysiteforme.admin.controller;

import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.entity.Vegetable;
import com.mysiteforme.admin.service.VegetableService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.entity.Cart;
import com.mysiteforme.admin.service.CartService;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.mysiteforme.admin.annotation.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * 购物车  前端控制器
 * </p>
 *
 * @author jing
 * @since 2023-01-31
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private VegetableService vegetableService;

    @Autowired
    private VegetableController vegetableController;

    @GetMapping("list")
    @SysLog("跳转购物车列表")
    public String list(Model model,HttpServletRequest request){
        vegetableController.category(model);
        //购物车
        User currentUser = (User) request.getAttribute("currentUser");
        EntityWrapper<Cart> w = new EntityWrapper<>();
        w.eq("customer_id",currentUser.getId());
        List<Cart> carts = cartService.selectList(w);
        int sum = carts.stream().mapToInt(x -> (Integer.valueOf(x.getVcount())) * x.getVprice().intValue()).sum();
        model.addAttribute("carts",carts);
        model.addAttribute("sum",sum);
        return "/shop/cart";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求购物车列表数据")
    public LayerData<Cart> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Cart> layerData = new LayerData<>();
        EntityWrapper<Cart> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
        }
        Page<Cart> pageData = cartService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增购物车页面")
    public String add(){
        return "/admin/cart/add";
    }

    @PostMapping("add")
    @SysLog("保存新增购物车数据")
    @ResponseBody
    public RestResponse add(Cart cart){
        cartService.insert(cart);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑购物车页面")
    public String edit(Long id,Model model){
        Cart cart = cartService.selectById(id);
        model.addAttribute("cart",cart);
        return "/admin/cart/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑购物车数据")
    public RestResponse edit(Cart cart){
        if(null == cart.getId() || 0 == cart.getId()){
            return RestResponse.failure("ID不能为空");
        }
        cartService.updateById(cart);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除购物车数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Cart cart = cartService.selectById(id);
        cart.setDelFlag(true);
        cartService.updateById(cart);
        return RestResponse.success();
    }

    @PostMapping("addCart")
    @ResponseBody
    @SysLog("添加购物车")
    public RestResponse addCart(HttpServletRequest request,String vid, Integer count){
        Vegetable vegetable = vegetableService.selectById(vid);
        //当前登录用户
        User currentUser = (User) request.getAttribute("currentUser");

        //购物车是否已经有
        EntityWrapper<Cart> wrapper = new EntityWrapper<>();
        wrapper.eq("vid",vid);
        wrapper.eq("customer_id",currentUser.getId());
        List<Cart> cartList = cartService.selectList(wrapper);
        if(cartList.size() > 0){
            Cart cart = cartList.get(0);
            cart.setVcount(cart.getVcount()+count);
            cartService.updateById(cart);
        }else {
            Cart cart = new Cart();
            cart.setCustomerId(String.valueOf(currentUser.getId()));
            cart.setVid(vid);
            cart.setVprice(vegetable.getCurrentPrice());
            cart.setVname(vegetable.getName());
            cart.setVimg(vegetable.getImg());
            cart.setVcount(count);
            cartService.insert(cart);
        }
        return RestResponse.success();
    }

}