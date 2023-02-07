package com.mysiteforme.admin.controller;

import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.entity.MessageBoard;
import com.mysiteforme.admin.service.MessageBoardService;
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
 * 留言板  前端控制器
 * </p>
 *
 * @author wangl
 * @since 2023-02-07
 */
@Controller
@RequestMapping("/messageBoard")
public class MessageBoardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBoardController.class);

    @Autowired
    private MessageBoardService messageBoardService;

    @GetMapping("list")
    @SysLog("跳转留言板列表")
    public String list(){
        return "/shop/contact";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求留言板列表数据")
    public LayerData<MessageBoard> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<MessageBoard> layerData = new LayerData<>();
        EntityWrapper<MessageBoard> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String name = (String) map.get("name");
            if(StringUtils.isNotBlank(name)) {
                wrapper.like("name",name);
            }else{
                map.remove("name");
            }

        }
        Page<MessageBoard> pageData = messageBoardService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增留言板页面")
    public String add(){
        return "/admin/messageBoard/add";
    }

    @PostMapping("add")
    @SysLog("保存新增留言板数据")
    @ResponseBody
    public RestResponse add(MessageBoard messageBoard){
        messageBoardService.insert(messageBoard);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑留言板页面")
    public String edit(Long id,Model model){
        MessageBoard messageBoard = messageBoardService.selectById(id);
        model.addAttribute("messageBoard",messageBoard);
        return "/admin/messageBoard/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑留言板数据")
    public RestResponse edit(MessageBoard messageBoard){
        if(null == messageBoard.getId() || 0 == messageBoard.getId()){
            return RestResponse.failure("ID不能为空");
        }
        messageBoardService.updateById(messageBoard);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除留言板数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        MessageBoard messageBoard = messageBoardService.selectById(id);
        messageBoard.setDelFlag(true);
        messageBoardService.updateById(messageBoard);
        return RestResponse.success();
    }

}