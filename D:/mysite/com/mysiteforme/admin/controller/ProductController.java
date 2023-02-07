package com.mysiteforme.admin.controller;

import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.entity.Product;
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
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品表  前端控制器
 * </p>
 *
 * @author jing
 * @since 2022-11-09
 */
@Controller
@RequestMapping("/admin/product")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    @SysLog("跳转产品表列表")
    public String list(){
        return "/admin/product/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求产品表列表数据")
    public LayerData<Product> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Product> layerData = new LayerData<>();
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String name = (String) map.get("name");
            if(StringUtils.isNotBlank(name)) {
                wrapper.like("name",name);
            }else{
                map.remove("name");
            }

        }
        Page<Product> pageData = productService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增产品表页面")
    public String add(){
        return "/admin/product/add";
    }

    @PostMapping("add")
    @SysLog("保存新增产品表数据")
    @ResponseBody
    public RestResponse add(Product product){
        productService.insert(product);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑产品表页面")
    public String edit(Long id,Model model){
        Product product = productService.selectById(id);
        model.addAttribute("product",product);
        return "/admin/product/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑产品表数据")
    public RestResponse edit(Product product){
        if(null == product.getId() || 0 == product.getId()){
            return RestResponse.failure("ID不能为空");
        }
        productService.updateById(product);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除产品表数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Product product = productService.selectById(id);
        product.setDelFlag(true);
        productService.updateById(product);
        return RestResponse.success();
    }

}