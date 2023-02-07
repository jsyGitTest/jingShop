<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>JingShop - login</title>
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
                                <a href="index.ftl"><img src="${base}/static/assets/img/logo/logo4.png" alt=""></a>
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
                       <h3>登录 / 注册</h3>
                        <ul>
                            <li><a href="index">首页</a></li>
                            <li>登录注册</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>         
    </div>
    <!--breadcrumbs area end-->
    
    <!-- customer login start -->
    <div class="customer_login">
        <div class="container">
            <div class="row">
               <!--login area start-->
                <div class="col-lg-6 col-md-6">
                    <div class="account_form">
                        <h2>登录</h2>
                        <h4> ${error}</h4>
                        <form action="doLogin" method="post">
                            <p>
                                <label>用户名<span>*</span></label>
                                <input type="text" id="username" name="username">
                             </p>
                             <p>
                                <label>密码 <span>*</span></label>
                                <input type="password" id="password" name="password">
                             </p>
                            <div class="login_submit">
                               <a href="#">忘记密码?</a>
                                <label for="remember">
                                    <input id="rememberMe" name="rememberMe" type="checkbox">
                                    记住我
                                </label>
                                <button type="submit">登录</button>

                            </div>

                        </form>
                     </div>
                </div>
                <!--login area start-->

                <!--register area start-->
                <div class="col-lg-6 col-md-6">
                    <div class="account_form register">
                        <h2>注册</h2>
                        <form id="register" action="doRegister">
                            <p>
                                <label>用户名 <span>*</span></label>
                                <input type="text" id="loginName" name="loginName">
                             </p>
                            <p>
                                <label>外号 <span>*</span></label>
                                <input type="text" id="nickName" name="nickName">
                            </p>
                            <p>
                                <label>手机号 <span>*</span></label>
                                <input type="text" id="tel" name="tel">
                            </p>
                             <p>
                                <label>密码 <span>*</span></label>
                                <input type="password" id="password" name="password">
                             </p>
                            <div class="login_submit">
                                <button id="addUser" type="button">注册</button>
                            </div>
                        </form>
                    </div>
                </div>
                <!--register area end-->
            </div>
        </div>
    </div>
    <!-- customer login end -->


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
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>

<!-- Main JS -->
<script src="${base}/static/assets/js/main.js"></script>
    <script>
        layui.use(['layer', 'form'], function() {
            $("#addUser").on('click',function(){
                var count = $("#count").val();
                var id = $("#vid").val();
                $.post('/doRegister', $('#register').serialize(), function(res) {
                    if(res.success){
                        var layer = layui.layer;
                        layer.confirm("注册成功，去登录", {
                            icon: 6,
                            title: "注册"
                        }, function(index) {
                            location.reload();
                        });
                    }else{
                        layer.msg(res.message);
                    }
                });
            });
        });
    </script>

</body>

</html>