<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
<%@include file="inc/common_head.jsp"%>
<title>在线支付-微信支付</title>
<style type="text/css">
	.mytable input,.mytable img{vertical-align:middle;}
	.mytable td{text-align:center;}
</style>

</head>

<body>
	<%@include file="inc/header.jsp"%>
	<div class="block clearfix">
		<div class="AreaR">
			<div class="block box">
				<div class="blank"></div>
				<div id="ur_here">
					当前位置: <a href="index.jsp">首页</a>
					<code>&gt;</code>
					在线支付-微信支付
				</div>
			</div>
			<div class="blank"></div>
			<div class="box">
				<div class="box_1">
						<div class="userCenterBox boxCenterList clearfix" style="_height:1%;">
							<h5><span>订单信息</span></h5>
							<!-- 订单信息 -->
							<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
								<tr>
									<td width="15%" align="right">订单编号：</td>
									<td align="left"></td>
								</tr>
								<tr>
									<td align="right">订单金额：</td>
									<td align="left">元</td>
								</tr>
							</table>
							<div class="blank"></div>
							<h5><span>微信扫码支付</span></h5>
							<table class="mytable" width="100%" border="0" cellpadding="5" cellspacing="1"
								bgcolor="#dddddd">
								<tr>
									<td><div id="qrcode"></div></td>
								</tr>
							</table>
						</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>