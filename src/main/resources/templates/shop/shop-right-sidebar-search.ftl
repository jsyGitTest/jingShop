<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>JingShop - shop right sidebar</title>
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
                                        <li><a href="/index">首页</a></li>
                                        <li><a class="active" href="/vegetable/search">商城</a></li>
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
                        <h3>商城
                        </h3>
                        <ul>
                            <li><a href="/index">首页</a></li>
                            <li>搜索</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>         
    </div>
    <!--breadcrumbs area end-->
    
    <!--shop  area start-->
    <div class="shop_area mt-70 mb-70">
        <div class="container">
            <div class="row">
                <div class="col-lg-9 col-md-12">
                    <!--shop wrapper start-->
                    <!--shop toolbar start-->
                    <div class="shop_toolbar_wrapper">
                        <div class="shop_toolbar_btn">

                            <button data-role="grid_3" type="button" class="active btn-grid-3" data-toggle="tooltip" title="3"></button>

                            <button data-role="grid_4" type="button"  class=" btn-grid-4" data-toggle="tooltip" title="4"></button>

                            <button data-role="grid_list" type="button"  class="btn-list" data-toggle="tooltip" title="List"></button>
                        </div>
                        <div class="page_amount">
                            <p>${counts} 条结果</p>
                        </div>
                    </div>
                     <!--shop toolbar end-->
                     <div class="row shop_wrapper">
                         <#list vegetables as v>

                         <div class="col-lg-4 col-md-4 col-sm-6 col-12 ">
                                <div class="single_product">
                                    <div class="product_thumb">
                                        <a class="primary_img" href="/vegetable/detail?id=${v.id}"><img src="${v.img}" alt=""></a>
                                    </div>
                                    <div class="product_content grid_content">
                                        <h4 class="product_name"><a href="product-details.ftl">${v.name}</a></h4>
                                        <p><a href="#">${v.producer}</a></p>
                                        <div class="price_box">
                                            <span class="current_price">￥${v.currentPrice}</span>
                                            <span class="old_price">￥${v.oldPrice}</span>
                                        </div>
                                    </div>
                                </div>
                        </div>
                         </#list>

                    </div>

                    <div class="shop_toolbar t_bottom">
                        <div class="pagination">
                            <ul>
                                <li <#if page == '1'>class="current"</#if>><a href="/vegetable/search?page=1">1</a></li>
                                <li <#if page == '2'>class="current"</#if>><a href="/vegetable/search?page=2">2</a></li>
                                <li <#if page == '3'>class="current"</#if>><a href="/vegetable/search?page=3">3</a></li>
                                <li class="next"><a href="#">next</a></li>
                                <li><a href="#">>></a></li>
                            </ul>
                        </div>
                    </div>
                    <!--shop toolbar end-->
                    <!--shop wrapper end-->
                </div>
                <div class="col-lg-3 col-md-12">
                   <!--sidebar widget start-->
                    <aside class="sidebar_widget">
                        <div class="widget_inner">

                            <div class="widget_list widget_filter">
                                <h3>价格筛选</h3>
                                <form action="/vegetable/search">
                                    <div id="slider-range"></div>   
                                    <button type="submit">筛选</button>
                                    <input style="width: 100px;" type="price" name="price" id="amount" />
                                </form> 
                            </div>
                            <div class="widget_list widget_color">
                                <h3>产地筛选</h3>
                                <ul>
                                    <li>
                                        <a href="#">广东  <span>(6)</span></a>
                                    </li>
                                    <li>
                                        <a href="#">山东 <span>(8)</span></a>
                                    </li>
                                </ul>
                            </div>

                            <div class="widget_list tags_widget">
                                <h3>标签筛选</h3>
                                <div class="tag_cloud">
                                    <#list tags as t>
                                        <a href="#">${t}</a>
                                    </#list>
                                </div>
                            </div>

                        </div>
                    </aside>
                    <!--sidebar widget end-->
                </div>
            </div>
        </div>
    </div>
    <!--shop  area end-->
    
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



</body>

</html>