<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的购物车</title>
    <%@include file="inc/common_head.jsp" %>
</head>
<body>
<%@include file="inc/header.jsp" %>
<div class="block table">
    <div class="AreaR">
        <div class="block box">
            <div class="blank"></div>
            <div id="ur_here">
                当前位置: <a href="${root}/index.jsp">首页</a><code>&gt;</code>我的购物车
            </div>
        </div>
        <div class="blank"></div>
        <div class="box">
            <div class="box_1">
                <div class="userCenterBox boxCenterList clearfix"
                     style="_height:1%;">
                    <h5><span>我的购物车</span></h5>
                    <table width="100%" align="center" border="0" cellpadding="5"
                           cellspacing="1" bgcolor="#dddddd">
                        <tr>
                            <th bgcolor="#ffffff">商品名称</th>
                            <th bgcolor="#ffffff">市场价</th>
                            <th bgcolor="#ffffff">本店价</th>
                            <th bgcolor="#ffffff">购买数量</th>
                            <th bgcolor="#ffffff">小计</th>
                            <th bgcolor="#ffffff" width="160px">操作</th>
                        </tr>

                        <c:if test="${not empty requestScope.carts}">
                            <c:forEach items="${requestScope.carts}" var="cart">
                                <tr>
                                    <td bgcolor="#ffffff" align="center" style="width:300px;">
                                        <!-- 商品图片 -->
                                        <a href="javascript:;" target="_blank">
                                            <img style="width:80px; height:80px;"
                                                 src="${root}/${cart.good.imgurl}"
                                                 border="0" title="${cart.good.name}"/>
                                        </a><br/>
                                        <!-- 商品名称 -->
                                        <a href="javascript:;" target="_blank" class="f6">${cart.good.name}</a>
                                    </td>
                                    <td align="center" bgcolor="#ffffff">${cart.good.marketprice}元</td>
                                    <td align="center" bgcolor="#ffffff">${cart.good.estoreprice}元</td>
                                    <td align="center" bgcolor="#ffffff">
                                        <input value="${cart.buynum}" size="4" class="inputBg"
                                               onblur="udpateCart(this.value, ${cart.gid})"
                                               style="text-align:center;"/>
                                    </td>
                                    <td id="totalPrice${cart.gid}" align="center"
                                        bgcolor="#ffffff">${cart.good.estoreprice * cart.buynum}元
                                    </td>
                                    <td align="center" bgcolor="#ffffff">
                                        <a onclick="deleteGood(${cart.gid}, this)" class="f6">删除</a>
                                    </td>
                                </tr>
                                <c:set var="totalPrice"
                                       value="${cart.good.estoreprice * cart.buynum + totalPrice}"></c:set>
                                <c:set var="savePrice"
                                       value="${savePrice + cart.buynum * (cart.good.marketprice - cart.good.estoreprice)}"></c:set>

                            </c:forEach>
                        </c:if>


                        <tr>
                            <td colspan="6" style="text-align:right;padding-right:10px;font-size:25px;">
                                购物金额小计&nbsp;<font color="red" id="total">${totalPrice}</font>元，
                                共为您节省了&nbsp;<font color="red" id="savePrice">${savePrice}</font>元
                                <a href="${root}/orders_submit.jsp"><input value="去结算" type="button" class="btn"/></a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="blank"></div>
    <div class="blank5"></div>
</div>
<%@include file="inc/footer.jsp" %>
</body>
<script src="${root}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    function udpateCart(val, gid) {
        $.ajax({
            url: "${root}/servlet/cart?methodName=updateCart",
            type: "post",
            data: {
                "inputVal": val,
                "gid": gid
            },
            typeData: "json",
            success: function (res) {
                let data = eval('(' + res + ')');
                $("#totalPrice" + gid).html(data.totalPrice)
                $("#total").html(data.total)
                $("#savePrice").html(data.savePrice)
            }
        })
    }

    function deleteGood(gid, element) {
        console.log(gid)
        $.ajax({
            url: "${root}/servlet/cart?methodName=deleteGood",
            type: "get",
            typeData: "json",
            data: {"goodId": gid},
            success: function (res) {
                res = eval('(' + res + ')')
                element.parentElement.parentElement.remove()
                $("#total").html(res.total)
                $("#savePrice").html(res.savePrice)
            }
        })
    }
</script>
</html>
