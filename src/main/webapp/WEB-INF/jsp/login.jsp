<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/static/jquery-1.7.1.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
用户名：<input type="text" id="username" value="zhangsan"/><br/>
密码：<input type="password" id="password" value="zhangsan"/><br/>
<input type="button" value="登录" id="login">

<script type="text/javascript">
    $("#login").click(function (e) {
        var username = $("#username").val();
        var passwrod = $("#password").val();
        if (username == null || passwrod == null) {
            alert("用户名密码不能为空");
            return;
        }
        $.post("/login.json", {username: username, password: passwrod}, function (res) {
            console.log(res);
            if (res.result.code == 0) {
                window.location.href = "/index";
            } else {
                alert(res.result.detail);
            }
        });
    });

</script>
</body>
</html>
