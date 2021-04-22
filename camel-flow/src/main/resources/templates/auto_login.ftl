<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>${siteName}-自动登录</title>
    <meta name="keywords" content="${keywords}">
    <meta name="description" content="${description}">

    <link rel="shortcut icon" href="../static/favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="middle-box text-center animated fadeInRightBig">
                    <div class="spiner-example">
	                    <div class="sk-spinner sk-spinner-fading-circle">
	                        <div class="sk-circle1 sk-circle"></div>
	                        <div class="sk-circle2 sk-circle"></div>
	                        <div class="sk-circle3 sk-circle"></div>
	                        <div class="sk-circle4 sk-circle"></div>
	                        <div class="sk-circle5 sk-circle"></div>
	                        <div class="sk-circle6 sk-circle"></div>
	                        <div class="sk-circle7 sk-circle"></div>
	                        <div class="sk-circle8 sk-circle"></div>
	                        <div class="sk-circle9 sk-circle"></div>
	                        <div class="sk-circle10 sk-circle"></div>
	                        <div class="sk-circle11 sk-circle"></div>
	                        <div class="sk-circle12 sk-circle"></div>
	                    </div>
                    </div>
                </div>
                <form id="form-login" class="m-t hidden" role="form" method="post" action="/login/do">
            
	                <div class="form-group">
	                    <input name="username" type="text" class="form-control" placeholder="用户名" required="" value="${username!''}">
	                </div>
	                <div class="form-group">
	                    <input name="password" type="password" class="form-control" placeholder="密码" required="" value="${password!''}">
	                </div>
	                <button id="btn-login" type="submit" class="btn btn-primary block full-width m-b">登 录</button>
	                
	            </form>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="/js/jquery.min.js?v=2.1.4"></script>
    <script src="/js/bootstrap.min.js?v=3.3.7"></script>

    <!-- 自定义js -->
    <script type="text/javascript">
		window.onload = function(){
			$("#form-login").submit();
		}
    </script>
</body>

</html>
