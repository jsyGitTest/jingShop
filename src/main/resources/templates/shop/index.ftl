<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Home</title>
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
                                    <li><a class="active"  href="index">首页</a></li>
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

<!--slider area start-->
<section class="slider_section">
    <div class="slider_area owl-carousel">
        <div class="single_slider d-flex align-items-center" data-bgimg="${base}/static/assets/img/slider/slider1.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="slider_content">
                            <h1>晶酱蔬菜</h1>
                            <h2>你身边的果蔬专家</h2>
                            <p>
                                我们不生产菜，我们只是黑心中间商
                            </p>
                            <a href="#">了解更多</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="single_slider d-flex align-items-center" data-bgimg="${base}/static/assets/img/slider/slider2.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="slider_content">
                            <h1>Fresh Vegetables</h1>
                            <h2>Natural Farm Products</h2>
                            <p>
                                Widest range of farm-fresh Vegetables, Fruits & seasonal produce
                            </p>
                            <a href="shop.html">Read more </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="single_slider d-flex align-items-center" data-bgimg="${base}/static/assets/img/slider/slider3.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="slider_content">
                            <h1>Fresh Tomatoes</h1>
                            <h2>Natural Farm Products</h2>
                            <p>
                                Natural organic tomatoes make your health stronger. Put your information here
                            </p>
                            <a href="shop.html">Read more </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--slider area end-->

<!--shipping area start-->
<div class="shipping_area">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="single_shipping">
                    <div class="shipping_icone">
                        <img src="${base}/static/assets/img/about/shipping1.jpg" alt="">
                    </div>
                    <div class="shipping_content">
                        <h3>免运费</h3>
                        <p>所有菜场附近订单或超过188元的订单免费送货</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="single_shipping col_2">
                    <div class="shipping_icone">
                        <img src="${base}/static/assets/img/about/shipping2.jpg" alt="">
                    </div>
                    <div class="shipping_content">
                        <h3>支持 8小时/5天</h3>
                        <p>早8晚6与我们联系，下班别打</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="single_shipping col_3">
                    <div class="shipping_icone">
                        <img src="${base}/static/assets/img/about/shipping3.jpg" alt="">
                    </div>
                    <div class="shipping_content">
                        <h3>30天退货</h3>
                        <p>30天菜都烂了还想退</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="single_shipping col_4">
                    <div class="shipping_icone">
                        <img src="${base}/static/assets/img/about/shipping4.jpg" alt="">
                    </div>
                    <div class="shipping_content">
                        <h3>不要赊账</h3>
                        <p>另代写暑假作业，高中不接</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--shipping area end-->

<!--product area start-->
<div class="product_area  mb-64">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="product_header">
                    <div class="section_title">
                        <p></p>
                        <h2>人气蔬菜 ʕ•̀ω•́ʔ</h2>
                    </div>

                </div>
            </div>
        </div>
        <div class="product_container">
            <div class="row">
                <div class="col-12">
                    <div class="tab-content">
                        <div class="tab-pane fade show active" id="plant1" role="tabpanel">
                            <div class="product_carousel product_column5 owl-carousel">
                                <#list hotVegetables as h>
                                <div class="product_items">
                                    <article class="single_product">
                                        <figure>
                                            <div class="product_thumb">
                                                <a class="primary_img" href="/vegetable/detail?id=${h.id}"><img src="${h.img}" alt=""></a>
<#--                                                <a class="secondary_img" href="product-details.ftl"><img src="${base}/static/assets/img/product/product2.jpg" alt=""></a>-->
                                                <div class="label_product">
                                                    <span class="label_sale">热度</span>
                                                    <span class="label_new">Top${h_index+1}</span>
                                                </div>
                                            </div>
                                            <figcaption class="product_content">
                                                <h4 class="product_name"><a href="/vegetable/detail?id=${h.id}">${h.name}</a></h4>
                                                <p><a href="#">${h.tag}</a></p>
                                                <div class="price_box">
                                                    <span class="current_price">￥${h.currentPrice}</span>
                                                    <span class="old_price">￥${h.oldPrice}</span>
                                                </div>
                                            </figcaption>
                                        </figure>
                                    </article>
                                </div>
                                </#list>
                            </div>
                            <div class="product_carousel product_column5 owl-carousel">
                                <#list hotVegetables as h>
                                    <#if (h_index > 4 )>
                                    <div class="product_items">
                                        <article class="single_product">
                                            <figure>
                                                <div class="product_thumb">
                                                    <a class="primary_img" href="/vegetable/detail?id=${h.id}"><img src="${h.img}" alt=""></a>
                                                    <#--                                                <a class="secondary_img" href="product-details.ftl"><img src="${base}/static/assets/img/product/product2.jpg" alt=""></a>-->
                                                    <div class="label_product">
                                                        <span class="label_sale">热度</span>
                                                        <span class="label_new">Top${h_index+1}</span>
                                                    </div>
                                                </div>
                                                <figcaption class="product_content">
                                                    <h4 class="product_name"><a href="/vegetable/detail?id=${h.id}">${h.name}</a></h4>
                                                    <p><a href="#">${h.tag}</a></p>
                                                    <div class="price_box">
                                                        <span class="current_price">￥${h.currentPrice}</span>
                                                        <span class="old_price">￥${h.oldPrice}</span>
                                                    </div>
                                                </figcaption>
                                            </figure>
                                        </article>
                                    </div>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--product area end-->




<!--banner fullwidth area satrt-->
<div class="banner_fullwidth">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="banner_full_content">
                    <p>疯狂星期六!</p>
                    <h2>打五折！！<span>所有菜！！</span></h2>
                </div>
            </div>
        </div>
    </div>
</div>
<!--banner fullwidth area end-->

<!--product area start-->
<div class="product_area mb-65">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section_title">
                    <h2>畅销蔬菜ʕ·͡ˑ·ཻʔ ʚɞ Ͽ҉</h2>
                </div>
            </div>
        </div>
        <div class="product_container">
            <div class="row">
                <div class="col-12">
                    <div class="product_carousel product_column5 owl-carousel">
                        <#list sellVegetables as s>
                            <article class="single_product">
                                <figure>
                                    <div class="product_thumb">
                                        <a class="primary_img" href="/vegetable/detail?id=${s.id}"><img src="${s.img}" alt=""></a>
                                        <div class="label_product">
                                            <span class="label_sale">热销</span>
                                            <span class="label_new">Top${s_index+1}</span>
                                        </div>
                                    </div>
                                    <figcaption class="product_content">
                                        <h4 class="product_name"><a href="/vegetable/detail?id=${s.id}">${s.name}</a></h4>
                                        <p><a href="#">${s.tag}</a></p>
                                        <div class="price_box">
                                            <span class="current_price">￥${s.currentPrice}</span>
                                            <span class="old_price">￥${s.oldPrice}</span>
                                        </div>
                                    </figcaption>
                                </figure>
                            </article>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--product area end-->

<!--blog area start-->
<section class="blog_section">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section_title">
                    <h2>文章</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="blog_carousel blog_column3 owl-carousel">
                <#list blogs as b>
                    <div class="col-lg-3">
                        <article class="single_blog">
                            <figure>
                                <div class="blog_thumb">
                                    <a href="/blog/detail?id=${b.id}"><img style="height: 250px;" src="${b.url}" alt=""></a>
                                </div>
                                <figcaption class="blog_content">
                                    <div class="articles_date">
                                        <p>${b.createDate?string('yyyy-MM-dd')} | ${b.author}</p>
                                    </div>
                                    <h4 class="post_title"><a href="/blog/detail?id=${b.id}">${b.title}</a></h4>
                                    <footer class="blog_footer">
                                        <a href="/blog/detail?id=${b.id}">了解更多</a>
                                    </footer>
                                </figcaption>
                            </figure>
                        </article>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</section>
<!--blog area end-->

<!--brand area end-->

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

<!-- modal area start-->
<div class="modal fade" id="modal_box" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true"><i class="icon-x"></i></span>
            </button>
            <div class="modal_body">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-5 col-md-5 col-sm-12">
                            <div class="modal_tab">
                                <div class="tab-content product-details-large">
                                    <div class="tab-pane fade show active" id="tab1" role="tabpanel" >
                                        <div class="modal_tab_img">
                                            <a href="#"><img src="${base}/static/assets/img/product/productbig1.jpg" alt=""></a>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="tab2" role="tabpanel">
                                        <div class="modal_tab_img">
                                            <a href="#"><img src="${base}/static/assets/img/product/productbig2.jpg" alt=""></a>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="tab3" role="tabpanel">
                                        <div class="modal_tab_img">
                                            <a href="#"><img src="${base}/static/assets/img/product/productbig3.jpg" alt=""></a>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="tab4" role="tabpanel">
                                        <div class="modal_tab_img">
                                            <a href="#"><img src="${base}/static/assets/img/product/productbig4.jpg" alt=""></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal_tab_button">
                                    <ul class="nav product_navactive owl-carousel" role="tablist">
                                        <li >
                                            <a class="nav-link active" data-toggle="tab" href="#tab1" role="tab" aria-controls="tab1" aria-selected="false"><img src="${base}/static/assets/img/product/product1.jpg" alt=""></a>
                                        </li>
                                        <li>
                                            <a class="nav-link" data-toggle="tab" href="#tab2" role="tab" aria-controls="tab2" aria-selected="false"><img src="${base}/static/assets/img/product/product6.jpg" alt=""></a>
                                        </li>
                                        <li>
                                            <a class="nav-link button_three" data-toggle="tab" href="#tab3" role="tab" aria-controls="tab3" aria-selected="false"><img src="${base}/static/assets/img/product/product2.jpg" alt=""></a>
                                        </li>
                                        <li>
                                            <a class="nav-link" data-toggle="tab" href="#tab4" role="tab" aria-controls="tab4" aria-selected="false"><img src="${base}/static/assets/img/product/product7.jpg" alt=""></a>
                                        </li>

                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-7 col-md-7 col-sm-12">
                            <div class="modal_right">
                                <div class="modal_title mb-10">
                                    <h2>Donec Ac Tempus</h2>
                                </div>
                                <div class="modal_price mb-10">
                                    <span class="new_price">$64.99</span>
                                    <span class="old_price" >$78.99</span>
                                </div>
                                <div class="modal_description mb-15">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Mollitia iste laborum ad impedit pariatur esse optio tempora sint ullam autem deleniti nam in quos qui nemo ipsum numquam, reiciendis maiores quidem aperiam, rerum vel recusandae </p>
                                </div>
                                <div class="variants_selects">
                                    <div class="variants_size">
                                        <h2>size</h2>
                                        <select class="select_option">
                                            <option selected value="1">s</option>
                                            <option value="1">m</option>
                                            <option value="1">l</option>
                                            <option value="1">xl</option>
                                            <option value="1">xxl</option>
                                        </select>
                                    </div>
                                    <div class="variants_color">
                                        <h2>color</h2>
                                        <select class="select_option">
                                            <option selected value="1">purple</option>
                                            <option value="1">violet</option>
                                            <option value="1">black</option>
                                            <option value="1">pink</option>
                                            <option value="1">orange</option>
                                        </select>
                                    </div>
                                    <div class="modal_add_to_cart">
                                        <form action="#">
                                            <input min="1" max="100" step="2" value="1" type="number">
                                            <button type="submit">add to cart</button>
                                        </form>
                                    </div>
                                </div>
                                <div class="modal_social">
                                    <h2>Share this product</h2>
                                    <ul>
                                        <li class="facebook"><a href="#"><i class="fa fa-facebook"></i></a></li>
                                        <li class="twitter"><a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li class="pinterest"><a href="#"><i class="fa fa-pinterest"></i></a></li>
                                        <li class="google-plus"><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                        <li class="linkedin"><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- modal area end-->




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


<style>
    .copyrights{text-indent:-9999px;height:0;line-height:0;font-size:0;overflow:hidden;}
</style>
<div class="copyrights">
    Collect from <a href="http://www.cssmoban.com/"  title="网站模板">模板之家</a>
    <a href="https://www.chazidian.com/"  title="查字典">查字典</a>
</div>
</body>

</html>