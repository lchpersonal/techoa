<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/static/jquery-1.7.1.min.js"></script>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <tr>
        <td>申请内容:</td>
        <td>
            <textarea id="content" cols="20" rows="5">家里有事</textarea>
        </td>
    </tr>
</table>
<input type="button" id="submit" value="提交"/>
<script type="text/javascript">
    $("#submit").click(function (e) {
        var content = $("#content").val();
        $.post("/techsupport/apply.json",
                {content: content},
                function (res) {
                    if (res.result.code == 0) {
                        alert("success");
                    } else {
                        alert("fail");
                    }
                });

    });
</script>
</body>
</html>
