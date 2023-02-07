package com.mysiteforme.admin.controller;

import com.mysiteforme.admin.entity.Category;
import com.mysiteforme.admin.service.CategoryService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.entity.CategorySec;
import com.mysiteforme.admin.service.CategorySecService;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 二级类目  前端控制器
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
@Controller
@RequestMapping("/admin/categorySec")
public class CategorySecController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategorySecController.class);

    @Autowired
    private CategorySecService categorySecService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    @SysLog("跳转二级类目列表")
    public String list(){
        return "/admin/categorySec/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求二级类目列表数据")
    public LayerData<CategorySec> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<CategorySec> layerData = new LayerData<>();
        EntityWrapper<CategorySec> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String name = (String) map.get("name");
            if(StringUtils.isNotBlank(name)) {
                wrapper.like("name",name);
            }else{
                map.remove("name");
            }

        }
        Page<CategorySec> pageData = categorySecService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增二级类目页面")
    public ModelAndView add(ModelAndView modelAndView){
        List<Category> categories = categoryService.selectList(new EntityWrapper<>());
        modelAndView.setViewName("/admin/categorySec/add");
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }

    @PostMapping("add")
    @SysLog("保存新增二级类目数据")
    @ResponseBody
    public RestResponse add(CategorySec categorySec){
        categorySecService.insert(categorySec);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑二级类目页面")
    public String edit(Long id,Model model){
        CategorySec categorySec = categorySecService.selectById(id);
        List<Category> categories = categoryService.selectList(new EntityWrapper<>());
        model.addAttribute("categorySec",categorySec);
        model.addAttribute("categories",categories);
        return "/admin/categorySec/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑二级类目数据")
    public RestResponse edit(CategorySec categorySec){
        if(null == categorySec.getId() || 0 == categorySec.getId()){
            return RestResponse.failure("ID不能为空");
        }
        categorySecService.updateById(categorySec);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除二级类目数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        CategorySec categorySec = categorySecService.selectById(id);
        categorySec.setDelFlag(true);
        categorySecService.updateById(categorySec);
        return RestResponse.success();
    }

}