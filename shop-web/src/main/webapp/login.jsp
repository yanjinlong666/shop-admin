<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<form enctype="application/x-www-form-urlencoded">
    用户名:<input type="text" id="userName"/>
    密码:<input type="password" id="userPwd"/>
    验证码:<input type="text" id="imgCode"/>
    <img src="<%=request.getContextPath()%>/imgcode" id="imgCodeInfo">
    <a href="javascript:;" onclick="refreshCode();">刷新</a>
<input type="button" value="登录" onclick="login();"/>
<input type="reset" value="取消"/>
</form>

路径:<%=request.getContextPath() %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/md5.js"></script>
<script type="text/javascript">

    function refreshCode() {
        var t = new Date().getTime();
        $("#imgCodeInfo").attr("src", "<%=request.getContextPath()%>/imgcode?"+t);
    }

function login() {
	var v_userName = $("#userName").val();
	var v_userPwd = hex_md5($("#userPwd").val());
	var v_imgCode = $("#imgCode").val();
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/user/login.jhtml",
		data:{
		    "userName":v_userName,
            "userPwd":v_userPwd,
            "imgCode":v_imgCode
        },
		success:function(result) {
			if (result.code == 200) {
				location.href="<%=request.getContextPath()%>/product/toList.jhtml";
			} else {
				alert(result.message);
			}
		}
	})
}
</script>
</body>
</html>