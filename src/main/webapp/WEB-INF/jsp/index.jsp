<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/static/jquery-1.7.1.min.js"></script>
<html>
<head>
    <title></title>
</head>
<body>

<table>
    <tr>
        <td>类型:</td>
        <td>
            <select id="type" name="type">
                <option value="0">年假</option>
                <option value="1">事假</option>
                <option value="2">病假</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>开始时间:</td>
        <td>
            <input type="text" id="startTime" value="2016-11-16 12:00:00"/>
        </td>
    </tr>
    <tr>
        <td>结束时间:</td>
        <td>
            <input type="text" id="endTime" value="2016-11-19 12:00:00"/>
        </td>
    </tr>
    <tr>
        <td>事由:</td>
        <td>
            <textarea id="reason" cols="20" rows="5">家里有事</textarea>
        </td>
    </tr>
</table>
<input type="button" id="submit" value="提交"/>
<script type="text/javascript">
    $("#submit").click(function (e) {
        var type = $("#type").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var reason = $("#reason").val();
        $.post("/leave/apply.json",
                {type: type, startTime: startTime, endTime: endTime, reason: reason},
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
