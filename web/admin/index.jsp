<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${root}/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${root}/easyui/themes/icon.css">
    <script type="text/javascript" src="${root}/easyui/jquery.min.js">
    </script>
    <script type="text/javascript" src="${root}/easyui/jquery.easyui.min.js">
    </script>
</head>
<body class="easyui-layout" >
<div data-options="region:'north',title:'estore商城管理系统',split:true,collapsed:true" style="height:100px;"></div>
<div data-options="region:'south',title:'本站信息',split:true,collapsed:true" style="height:100px;"></div>
<div data-options="region:'west',title:'系统管理',split:true" style="width:200px;">
    <ul id="tt" class="easyui-tree">
        <li>
            <span>后台管理</span>
            <ul>
                <li>
                    <span>商品管理</span>
                    <ul>
                        <li><span>查询商品</span></li>
                    </ul>
                </li>
                <li>
                    <span>类型管理</span>
                    <ul>
                        <li><span>查询类型</span></li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</div>
<div data-options="region:'center',title:'管理界面'" style="padding:5px;background:#eee;">

</div>
</body>
</html>
<script type="text/javascript" >

</script>