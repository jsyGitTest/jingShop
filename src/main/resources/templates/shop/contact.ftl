<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>JingShop - contact page</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="${base}/static/assets/img/favicon.ico">
    
     <!-- CSS 
    ========================= -->
    <!--bootstrap min css-->
    <link rel="stylesheet" href="${base}/static/assets/css/bootstrap.min.css">
    <!--owl carousel min css-->
    <link rel="stylesheet" href="${base}/static/assets/css/owl.carousel.min.css">
    <!--slick min css-->
    <link rel="stylesheet" href="${base}/static/assets/css/slick.css">
    <!--magnific popup min css-->
    <link rel="stylesheet" href="${base}/static/assets/css/magnific-popup.css">
    <!--font awesome css-->
    <link rel="stylesheet" href="${base}/static/assets/css/font.awesome.css">
    <!--ionicons css-->
    <link rel="stylesheet" href="${base}/static/assets/css/ionicons.min.css">
    <!--linearicons css-->
    <link rel="stylesheet" href="${base}/static/assets/css/linearicons.css">
    <!--animate css-->
    <link rel="stylesheet" href="${base}/static/assets/css/animate.css">
    <!--jquery ui min css-->
    <link rel="stylesheet" href="${base}/static/assets/css/jquery-ui.min.css">
    <!--slinky menu css-->
    <link rel="stylesheet" href="${base}/static/assets/css/slinky.menu.css">
    <!--plugins css-->
    <link rel="stylesheet" href="${base}/static/assets/css/plugins.css">
    
    <!-- Main Style CSS -->
    <link rel="stylesheet" href="${base}/static/assets/css/style.css">
    
    <!--modernizr min js here-->
    <script src="${base}/static/assets/js/vendor/modernizr-3.7.1.min.js"></script>

</head>

<body>


<header>
    <div class="main_header">
        <div class="header_middle">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-2">
                        <div class="logo">
                            <a href="/index"><img src="${base}/static/assets/img/logo/logo5.png" alt=""></a>
                        </div>
                    </div>
                    <div class="col-lg-10">
                        <div class="header_right_info" style="display:flex; align-items: center;padding-left: 100px;">
                            <div class="search_container">
                                <form action="/vegetable/search">
                                    <div class="search_box">
                                        <input placeholder="搜索蔬菜..." id="name" name="name" type="text">
                                        <button type="submit"><span class="lnr lnr-magnifier"></span></button>
                                    </div>
                                </form>
                            </div>
                            <div class="header_account_area">
                                <div class="header_account_list register">
                                    <ul>
                                        <#if currentUser.nickName!''>
                                            <li><a href="/login">${currentUser.nickName}</a></li>
                                            <li><span>/</span></li>
                                            <li><a href="/systemLogout">退出</a></li>
                                        <#else>
                                            <li><a href="/login">注册</a></li>
                                            <li><span>/</span></li>
                                            <li><a href="/login">登录</a></li>
                                        </#if>

                                    </ul>
                                </div>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="header_bottom sticky-header">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-3">
                        <div class="categories_menu">
                            <div class="categories_title">
                                <h2 class="categori_toggle">所有类目</h2>
                            </div>
                            <div class="categories_menu_toggle">
                                <ul>
                                    <#list categoryList as c>
                                        <#if (c.categorySecs?size > 0)>
                                            <li class="menu_item_children"><a href="/vegetable/search?categoryFirst=${c.id}">${c.name}<i class="fa fa-angle-right"></i></a>
                                            <ul class="categories_mega_menu">
                                                <#list c.categorySecs as cc>
                                                    <li class="menu_item_children"><a href="/vegetable/search?categorySecond=${cc.id}">${cc.name}</a></li>
                                                </#list>
                                            </ul>
                                        </#if>
                                        <#if (c.categorySecs?size == 0)>
                                            <li><a href="/vegetable/search?categoryFirst=${c.id}">${c.name}</a></li>
                                        </#if>

                                    </#list>

                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <!--main menu start-->
                        <div class="main_menu menu_position">
                            <nav>
                                <ul>
                                    <li><a class="active"  href="/index">首页</a></li>
                                    <li><a href="/vegetable/search">商城</a></li>
                                    <li><a href="/blog/list">文章</a></li>
                                    <li><a href="/veorder/getOrder">我的账户</a></li>
                                    <li><a href="/messageBoard/list">联系我们</a></li>
                                </ul>
                            </nav>
                        </div>
                        <!--main menu end-->
                    </div>
                    <div class="col-lg-3">
                        <div class="call-support">
                            <p><a href="tel:(08)23456789">(010) PG 12 123 </a>打给我好吗？</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
    <!--header area end-->

    <!--breadcrumbs area start-->
    <div class="breadcrumbs_area">
        <div class="container">   
            <div class="row">
                <div class="col-12">
                    <div class="breadcrumb_content">
                       <h3>联系我们</h3>
                        <ul>
                            <li><a href="/index">首页</a></li>
                            <li>留言板</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>         
    </div>
    <!--breadcrumbs area end-->

    <!--contact map start-->
    <div class="contact_map mt-70">

    </div>
    <!--contact map end-->

    <!--contact area start-->
    <div class="contact_area">
        <div class="container">   
            <div class="row">
                <div class="col-lg-6 col-md-6">
                   <div class="contact_message content">
                        <h3>留言</h3>
                         <p>有啥意见就说，反正我们也不改，你别在气出个好歹。</p>
                        <ul>
                            <li><i class="fa fa-fax"></i>王子路与公主路交界处</li>
                            <li><i class="fa fa-phone"></i> <a href="#">0(1234) 567 890</a></li>
                            <li><i class="fa fa-envelope-o"></i><a>bupiannipianshui.com</a>  </li>
                        </ul>             
                    </div> 
                </div>
                <div class="col-lg-6 col-md-6">
                   <div class="contact_message form">
                        <h3>提出你宝贵的意见</h3>
                        <form id="msg" method="POST">
                            <p>  
                               <label> 你的大名</label>
                                <input name="name" id="name" placeholder="Name *" type="text">
                            </p>
                            <p>       
                               <label> 📮 你的邮箱</label>
                                <input name="email" id="email" placeholder="Email *" type="email">
                            </p>
                            <p>          
                               <label> 标题</label>
                                <input name="title" id="title" placeholder="Subject *" type="text">
                            </p>    
                            <div class="contact_textarea">
                                <label>  留言内容</label>
                                <textarea placeholder="Message *" id="content" name="content"  class="form-control2" ></textarea>
                            </div>   
                            <button type="button" id="sendMsg"> Send</button>
                            <p class="form-messege"></p>
                        </form> 

                    </div> 
                </div>
            </div>
        </div>    
    </div>

    <!--contact area end-->


<!--footer area start-->
<footer class="footer_widgets">
    <div class="footer_top">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-12 col-sm-7">
                    <div class="widgets_container contact_us">
                        <p class="footer_desc">因为经常卖过期蔬菜被人腿都打断了<br>所以我们不会告诉你地址.</p>
                        <p><span>地址:</span>不知道</p>
                        <p><span>邮箱:</span> <a href="#">jing@bupiannipianshui.com</a></p>
                        <p><span>电话:</span> <a href="tel:(08)23456789">没有</a> </p>
                    </div>
                </div>
                <div class="col-lg-2 col-md-3 col-sm-5">
                    <div class="widgets_container widget_menu">
                        <h3>信息</h3>
                        <div class="footer_menu">

                            <ul>
                                <li><a href="about.ftl">关于我们</a></li>
                                <li><a href="#">交货信息</a></li>
                                <li><a href="#">隐私政策</a></li>
                                <li><a href="#">条款和条件</a></li>
                                <li><a href="contact.ftl">联系我们</a></li>
                                <li><a href="#">位置</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-3 col-sm-4">
                    <div class="widgets_container widget_menu">
                        <h3>附加信息</h3>
                        <div class="footer_menu">
                            <ul>
                                <li><a href="#">品牌</a></li>
                                <li><a href="#">礼券</a></li>
                                <li><a href="#">特价商品</a></li>
                                <li><a href="#">烂菜叶子</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-8">
                    <div class="widgets_container widget_newsletter">
                        <h3>留下你的邮箱📮</h3>
                        <p class="footer_desc">有卖不掉的菜会通知你的</p>
                        <div class="subscribe_form">
                            <form id="mc-form" class="mc-form footer-newsletter" >
                                <input id="mc-email" type="email" autocomplete="off" placeholder="Enter you email" />
                                <button id="mc-submit">订阅</button>
                            </form>
                            <!-- mailchimp-alerts Start -->
                            <div class="mailchimp-alerts text-centre">
                                <div class="mailchimp-submitting"></div><!-- mailchimp-submitting end -->
                                <div class="mailchimp-success"></div><!-- mailchimp-success end -->
                                <div class="mailchimp-error"></div><!-- mailchimp-error end -->
                            </div><!-- mailchimp-alerts end -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="footer_bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-6 col-md-7">
                    <div class="copyright_area">
                        <p>Copyright &copy; 2023.晶酱蔬菜我的最爱.</p>
                    </div>
                </div>
                <div class="col-lg-6 col-md-5">
                    <div class="footer_payment">
                        <ul>
                            <li><a href="#"><img src="${base}/static/assets/img/icon/paypal1.jpg" alt=""></a></li>
                            <li><a href="#"><img src="${base}/static/assets/img/icon/paypal2.jpg" alt=""></a></li>
                            <li><a href="#"><img src="${base}/static/assets/img/icon/paypal3.jpg" alt=""></a></li>
                            <li><a href="#"><img src="${base}/static/assets/img/icon/paypal4.jpg" alt=""></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!--footer area end-->



<!-- JS
============================================ -->

<!--map js code here-->
<script src="https://ditu.google.cn/maps/api/js?sensor=false&amp;libraries=geometry&amp;v=3.22&amp;key=AIzaSyChs2QWiAhnzz0a4OEhzqCXwx_qA9ST_lE"></script>
<script  src="https://ditu.google.cn/jsapi"></script>
<script src="${base}/static/assets/js/map.js"></script>

<!--jquery min js-->
<script src="${base}/static/assets/js/vendor/jquery-3.4.1.min.js"></script>
<!--popper min js-->
<script src="${base}/static/assets/js/popper.js"></script>
<!--bootstrap min js-->
<script src="${base}/static/assets/js/bootstrap.min.js"></script>
<!--owl carousel min js-->
<script src="${base}/static/assets/js/owl.carousel.min.js"></script>
<!--slick min js-->
<script src="${base}/static/assets/js/slick.min.js"></script>
<!--magnific popup min js-->
<script src="${base}/static/assets/js/jquery.magnific-popup.min.js"></script>
<!--counterup min js-->
<script src="${base}/static/assets/js/jquery.counterup.min.js"></script>
<!--jquery countdown min js-->
<script src="${base}/static/assets/js/jquery.countdown.js"></script>
<!--jquery ui min js-->
<script src="${base}/static/assets/js/jquery.ui.js"></script>
<!--jquery elevatezoom min js-->
<script src="${base}/static/assets/js/jquery.elevatezoom.js"></script>
<!--isotope packaged min js-->
<script src="${base}/static/assets/js/isotope.pkgd.min.js"></script>
<!--slinky menu js-->
<script src="${base}/static/assets/js/slinky.menu.js"></script>
<!-- Plugins JS -->
<script src="${base}/static/assets/js/plugins.js"></script>

<!-- Main JS -->
<script src="${base}/static/assets/js/main.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>

<!-- Main JS -->
<script>
    layui.use(['layer', 'form'], function() {
        $("#sendMsg").on('click',function(){
            var count = $("#count").val();
            var id = $("#vid").val();
            $.post('/messageBoard/add', $('#msg').serialize(), function(res) {
                if(res.success){
                    layer.msg("留言成功");
                }else{
                    layer.msg(res.message);
                }
            });
        });
    });
</script>

</body>

</html>