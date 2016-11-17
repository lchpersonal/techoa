<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="/static/jquery-1.7.1.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${leave!=null}">
    <table>
        <tr>
            <td>申请人</td>
            <td>${leave.username}</td>
        </tr>
        <tr>
            <td>类型:</td>
            <td>${leave.type}</td>
        </tr>
        <tr>
            <td>开始时间:</td>
            <td>${leave.startTime}</td>
        </tr>
        <tr>
            <td>结束时间:</td>
            <td>${leave.endTime}</td>
        </tr>
        <tr>
            <td>事由:</td>
            <td>${leave.reason}</td>
        </tr>
        <tr>
            <td>审批</td>
            <td>
                <input class="oper" type="button" value="批准" taskId="${taskId}" leaveId="${leave.id}"
                       id="yes"/>
                <input class="oper" type="button" value="不批准" taskId="${taskId}" leaveId="${leave.id}"
                       id="no"/>
            </td>
        </tr>
    </table>
</c:if>
<c:if test="${leave==null}">
    没有任何需要操作的任务
</c:if>
<script type="text/javascript">
    $(".oper").click(function () {
        var that = $(this);
        var taskId = that.attr("taskId");
        var leaveId = that.attr("leaveId");
        var val = that.val();
        $.post("/leave/oper.json", {taskId: taskId, leaveId: leaveId, result: val == "批准"}, function (res) {
            if (res.result.code == 0) {
                alert("成功");
            } else {
                alert("失败");
            }
        });
    });
</script>
</body>
</html>
