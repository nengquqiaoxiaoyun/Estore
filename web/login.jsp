<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>登录</title>
    <%@include file="inc/common_head.jsp" %>
</head>
<body>
<%@include file="inc/header.jsp" %>


<div class="block block1">
    <div class="blank"></div>
    <div class="usBox clearfix">
        <div class="usBox_1">
            <div class="login_tab">
                <ul>
                    <li class="active">用户登录</li>
                    <li onclick="location.href='${root}/register.jsp';">
                        <a href="javascript:;">用户注册</a>
                    </li>
                </ul>
            </div>
            <form name="formLogin" action="${root}/servlet/user?methodName=login" method="post"
                  onSubmit="return userLogin()">
                <table width="100%" border="0" align="left" cellpadding="3"
                       cellspacing="5">
                    <caption style="color: red">${msg}</caption>
                    <tr>
                        <td width="25%" align="right">用户名</td>
                        <td width="65%"><input name="phone" type="text" size="25"
                                               class="inputBg" value="${cookie.phone.value}"/></td>
                    </tr>
                    <tr>
                        <td align="right">密码</td>
                        <td><input name="password" type="password" size="15"
                                   class="inputBg"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="checkbox" value="on" name="remember"
                                   id="remember"/><label for="remember">记住用户名</label></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="left">
                            <input type="submit" name="submit" value="" class="us_Submit"/>
                        </td>
                    </tr>
                </table>
            </form>
            <div class="blank"></div>
        </div>
    </div>
    <%@include file="inc/footer.jsp" %>
</body>
</html>