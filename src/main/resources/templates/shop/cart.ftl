<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>JingShop - cart page</title>
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
            <div class="header_top">
                <div class="container">

                </div>
            </div>
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
                                                <li><a href="/login">${currentUser.nickName} </a></li>
                                                <li><span>/</span></li>
                                                <li><a href="/systemLogout">退出</a></li>
                                            <#else>
                                                <li><a href="/login">注册</a></li>
                                                <li><span>/</span></li>
                                                <li><a href="/login">登录</a></li>
                                            </#if>

                                        </ul>
                                    </div>

                                    <div class="header_account_list  mini_cart_wrapper">
                                        <a href="/cart/list"><span class="lnr lnr-cart"></span><span class="item_count">${carts?size}</span></a>
                                        <!--mini cart-->
                                        <div class="mini_cart">
                                            <div class="cart_gallery">
                                                <#list carts as c>
                                                    <div class="cart_item">
                                                        <div class="cart_img">
                                                            <a href="/vegetable/detail?id=${c.vid}"><img src="${c.vimg}" alt=""></a>
                                                        </div>
                                                        <div class="cart_info">
                                                            <a href="#">${c.vname}</a>
                                                            <p>${c.vcount} x <span> ${c.vprice} </span></p>
                                                        </div>
                                                        <div class="cart_remove">
                                                            <a href="#"><i class="icon-x"></i></a>
                                                        </div>
                                                    </div>
                                                </#list>
                                            </div>
                                            <#if (carts?size > 0)>
                                                <div class="mini_cart_table">
                                                    <div class="cart_table_border">
                                                        <div class="cart_total">
                                                            <span>合计:</span>
                                                            <span class="price">${sum} 块钱</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </#if>
                                            <div class="mini_cart_footer">
                                                <div class="cart_button">
                                                    <a href="/cart/list"><i class="fa fa-shopping-cart"></i>查看购物车</a>
                                                </div>
                                            </div>
                                        </div>
                                        <!--mini cart end-->
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
                                        <li><a  href="/index">首页</a></li>
                                        <li><a  href="/vegetable/search">商城</a></li>
                                        <li><a href="/blog/list">文章</a></li>
                                        <li><a  href="/veorder/getOrder">我的账户</a></li>
                                        <li><a href="/messageBoard/list">联系我们</a></li>

                                    </ul>
                                </nav>
                            </div>
                            <!--main menu end-->
                        </div>
                        <div class="col-lg-3">
                            <div class="call-support">
                                <p><a href="tel:(08)23456789">(08) 23 456 789</a>客户支持</p>
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
                       <h3>购物车</h3>
                        <ul>
                            <li><a href="index.ftl">首页</a></li>
                            <li>菜菜购物车</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--breadcrumbs area end-->
    
     <!--shopping cart area start -->
    <div class="shopping_cart_area mt-70">
        <div class="container">  
            <form action="#"> 
                <div class="row">
                    <div class="col-12">
                        <div class="table_desc">
                            <div class="cart_page table-responsive">
                                <table>
                            <thead>
                                <tr>
                                    <th class="product_remove">图片</th>
                                    <th class="product_thumb">名称</th>
                                    <th class="product_name">价格</th>
                                    <th class="product-price">数量</th>
                                    <th class="product_quantity">总计</th>
                                    <th class="product_total">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#list carts as c>
                                    <tr>
                                        <td class="product_thumb"><a href="#"><img src="${c.vimg}" alt=""></a></td>
                                        <td class="product_name"><a href="#">${c.vname}</a></td>
                                        <td class="product-price">${c.vprice}</td>
                                        <td class="product_quantity"><label>数量</label> <input min="1" max="100" value="${c.vcount}" type="number"></td>
                                        <td class="product_total">${c.vprice*c.vcount}</td>
                                        <td class="product_remove"><a href="#"><i class="fa fa-trash-o"></i></a></td>
                                    </tr>
                                </#list>
                            </tbody>

                        </table>
                                <div class="cart_submit">
                                    <button type="submit">update cart</button>
                                </div>
                            </div>

                        </div>
                     </div>
                 </div>
                 <!--coupon code area start-->
                <div class="coupon_area">
                    <div class="row">
                        <div class="col-lg-6 col-md-6">
                            <div class="coupon_code left">
                                <h3>优惠券</h3>
                                <div class="coupon_inner">
                                    <p>输入优惠券兑换码.</p>
                                    <input placeholder="Coupon code" type="text">
                                    <button type="submit">应用</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="coupon_code right">
                                <h3>结算</h3>
                                <div class="coupon_inner">
                                   <div class="cart_subtotal">
                                       <p>总计</p>
                                       <p class="cart_amount">${sum} 元</p>
                                   </div>
                                   <div class="cart_subtotal ">
                                       <p>优惠券</p>
                                       <p class="cart_amount">-</p>
                                   </div>
                                   <div class="checkout_btn">
                                       <a id="addOrder" href="#">下单</a>
                                   </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--coupon code area end-->
            </form> 
        </div>     
    </div>
     <!--shopping cart area end -->
    
    <!--footer area start-->
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

    <!--footer area end-->

<!-- JS
============================================ -->
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
    <script>
        layui.use(['layer', 'form'], function() {
            $("#addOrder").on('click',function(){
                $.post('/veorder/add', {}, function(res) {
                    if(res.success){
                        layer.msg("下单成功");
                    }else{
                        layer.msg(res.message);
                    }
                });
            });
        });
    </script>


</body>

</html>