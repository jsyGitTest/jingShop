package com.mysiteforme.admin.controller;

import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.entity.Category;
import com.mysiteforme.admin.service.CategoryService;
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
 * 类目表  前端控制器
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    @SysLog("跳转类目表列表")
    public String list(){
        return "/admin/category/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求类目表列表数据")
    public LayerData<Category> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Category> layerData = new LayerData<>();
        EntityWrapper<Category> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String name = (String) map.get("name");
            if(StringUtils.isNotBlank(name)) {
                wrapper.like("name",name);
            }else{
                map.remove("name");
            }

        }
        Page<Category> pageData = categoryService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增类目表页面")
    public String add(){
        return "/admin/category/add";
    }

    @PostMapping("add")
    @SysLog("保存新增类目表数据")
    @ResponseBody
    public RestResponse add(Category category){
        categoryService.insert(category);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑类目表页面")
    public String edit(Long id,Model model){
        Category category = categoryService.selectById(id);
        model.addAttribute("category",category);
        return "/admin/category/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑类目表数据")
    public RestResponse edit(Category category){
        if(null == category.getId() || 0 == category.getId()){
            return RestResponse.failure("ID不能为空");
        }
        categoryService.updateById(category);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除类目表数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Category category = categoryService.selectById(id);
        category.setDelFlag(true);
        categoryService.updateById(category);
        return RestResponse.success();
    }

}