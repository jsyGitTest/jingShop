package com.mysiteforme.admin.controller;

import com.mysiteforme.admin.entity.*;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.service.BlogService;
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
 * 文章  前端控制器
 * </p>
 *
 * @author wangl
 * @since 2023-02-06
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @GetMapping("list")
    @SysLog("跳转文章列表")
    public String list(Model model){
        List<Blog> blogs = blogService.selectList(new EntityWrapper<>());
        model.addAttribute("blogs",blogs);
        return "/shop/blog-fullwidth";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求文章列表数据")
    public LayerData<Blog> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Blog> layerData = new LayerData<>();
        EntityWrapper<Blog> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String title = (String) map.get("title");
            if(StringUtils.isNotBlank(title)) {
                wrapper.like("title",title);
            }else{
                map.remove("title");
            }

        }
        Page<Blog> pageData = blogService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增文章页面")
    public String add(){
        return "/admin/blog/add";
    }

    @PostMapping("add")
    @SysLog("保存新增文章数据")
    @ResponseBody
    public RestResponse add(Blog blog){
        blogService.insert(blog);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑文章页面")
    public String edit(Long id,Model model){
        Blog blog = blogService.selectById(id);
        model.addAttribute("blog",blog);
        return "/admin/blog/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑文章数据")
    public RestResponse edit(Blog blog){
        if(null == blog.getId() || 0 == blog.getId()){
            return RestResponse.failure("ID不能为空");
        }
        blogService.updateById(blog);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除文章数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        Blog blog = blogService.selectById(id);
        blog.setDelFlag(true);
        blogService.updateById(blog);
        return RestResponse.success();
    }

    @GetMapping("detail")
    @SysLog("跳转蔬菜详情")
    public String detail(HttpServletRequest request, Model model, String id){
        Blog blog = blogService.selectById(id);
        model.addAttribute("blog",blog);
        return "/shop/blog-details";
    }

}