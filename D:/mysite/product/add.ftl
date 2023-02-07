<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>产品表添加--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="${site.description}"/>
    <meta name="keywords" content="${site.keywords}"/>
    <meta name="author" content="${site.author}"/>
    <link rel="icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <style type="text/css">
        .layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
        @media(max-width:1240px){
            .layui-form-item .layui-inline{ width:100%; float:none; }
        }
        .layui-form-item .role-box {
            position: relative;
        }
        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item">
        <label class="layui-form-label">产品名称</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="name"  placeholder="请输入产品名称">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否参加促销</label>
        <div class="layui-input-block">

            <@my type="product_is_cuxiao">
                <#list result as r>
                <input type="radio" name="isCuxiao"  value="${r.value}" title="${r.label}">
                </#list>
            </@my>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产品来源</label>
        <div class="layui-input-block">

            <select name="source" >
                <option value="" selected="">请选择产品来源</option>
                <@my type="product_source">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">

            <input type="checkbox" name="pStatus"  lay-skin="switch" value="1" lay-text="是|否" >

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产品描述</label>
        <div class="layui-input-block">

            <textarea name="description"  placeholder="请输入产品描述" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上架时间</label>
        <div class="layui-input-block">

            <input type="text" name="createTime" id="createTime"   lay-verify="date" placeholder="请选择上架时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addProduct">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer','laydate'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                laydate = layui.laydate,
                layer = layui.layer;

                          //初始赋值
                          laydate.render({
                            elem: '#createTime'
                          });

        form.on("submit(addProduct)",function(data){
                     if(undefined === data.field.pStatus || '0' === data.field.pStatus || null === data.field.pStatus){
                    data.field.pStatus = false;
                }else{
                    data.field.pStatus = true;
                }

                       if(null === data.field.createTime || "" ===data.field.createTime){
                        delete data.field["createTime"];
                    }else{
                        data.field.createTime = new Date(data.field.createTime);
                    }

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/admin/product/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("产品表添加成功！",{time:1000},function(){
                        parent.layer.close(parent.addIndex);
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>