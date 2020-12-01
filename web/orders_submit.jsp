<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="Generator" content="ECSHOP v2.7.3"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="Keywords" content=""/>
    <meta name="Description" content=""/>
    <title>提交订单</title>
    <%@include file="inc/common_head.jsp" %>
</head>
<body>
<%@include file="inc/header.jsp" %>
<div class="block clearfix">
    <div class="AreaR">
        <div class="block box">
            <div class="blank"></div>
            <div id="ur_here">
                当前位置: <a href="index.jsp">首页</a><code>&gt;</code>购物流程
            </div>
        </div>
        <div class="blank"></div>
        <div class="box">
            <div class="box_1">
                <div class="userCenterBox boxCenterList clearfix" style="_height:1%;">
                    <form action="${root}/servlet/order?methodName=subOrder" method="post">
                        <!---------收货人信息开始---------->
                        <h5><span>收货人信息</span></h5>
                        <table width="100%" align="center" border="0" cellpadding="5"
                               cellspacing="1" bgcolor="#dddddd">
                            <tr>
                                <td bgcolor="#ffffff" align="right" width="120px">区域信息：</td>
                                <td bgcolor="#ffffff">
                                    <!-- 省 -->
                                    <select id="province">
                                        <option value="">-- 请选择省 --</option>
                                    </select>&nbsp;&nbsp;&nbsp;
                                    <!-- 市 -->
                                    <select id="city">
                                        <option value="" name="city">-- 请选择市 --</option>
                                    </select>&nbsp;&nbsp;&nbsp;
                                    <!-- 县(区) -->
                                    <select id="district">
                                        <option value="" name="district">-- 请选择县(区) --</option>
                                    </select>

                                    <!-- 给选择框赋值，option的value获取到的是value的值 -->
                                    <input id="ph" type="hidden" name="province">
                                    <input id="ch" type="hidden" name="city">
                                    <input id="dh" type="hidden" name="district">
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">详细地址：</td>
                                <td bgcolor="#ffffff">
                                    <input style="width:347px;" name="detailAddress"/>
                                </td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">邮政编码：</td>
                                <td bgcolor="#ffffff"><input name="zipcode"/></td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">收货人姓名：</td>
                                <td bgcolor="#ffffff"><input name="name"/></td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">联系电话：</td>
                                <td bgcolor="#ffffff"><input name="telephone"/></td>
                            </tr>
                        </table>
                        <!---------收货人信息结束---------->

                        <!----------商品列表开始----------->
                        <div class="blank"></div>
                        <h5><span>商品列表</span></h5>
                        <table width="100%" border="0" cellpadding="5" cellspacing="1"
                               bgcolor="#dddddd">
                            <tr>
                                <th width="30%" align="center">商品名称</th>
                                <th width="22%" align="center">市场价格</th>
                                <th width="22%" align="center">商品价格</th>
                                <th width="15%" align="center">购买数量</th>
                                <th align="center">小计</th>
                            </tr>
                            <c:if test="${not empty sessionScope.carts}"></c:if>
                            <c:forEach items="${sessionScope.carts}" var="cart">
                                <tr>
                                    <td>
                                        <a href="javascript:;" class="f6">${cart.good.name}</a>
                                    </td>
                                    <td>${cart.good.marketprice}元</td>
                                    <td>${cart.good.estoreprice}元</td>
                                    <td align="center">${cart.buynum}</td>
                                    <td>${cart.buynum * cart.good.estoreprice}元</td>
                                </tr>

                                <c:set var="total" value="${total + cart.buynum * cart.good.estoreprice}"></c:set>
                            </c:forEach>

                            <tr>
                                <td colspan="5" style="text-align:right;padding-right:10px;font-size:25px;">
                                    商品总价&nbsp;<font color="red">&yen;${total}</font>元
                                    <input type="submit" value="提交订单" class="btn"/>
                                </td>
                            </tr>
                        </table>
                        <!----------商品列表结束----------->
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="inc/footer.jsp" %>
</body>
<script src="${root}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">

    $.ajax({
        url: "${root}/servlet/order?methodName=listPCDByPid",
        data: {"pid": 0},
        type: "post",
        dateType: "json",
        success: function (res) {
            res = eval('(' + res + ')')
            //获取省的标签对象
            let $province = $("#province");
            //遍历json数组，数组中的每一个json都是省的信息
            for (let i = 0; i < res.length; i++) {
                // 创建下拉列表需要的option标签对象
                let $option = $("<option></option>");
                // 将option标签对象添加到下拉列表中
                $option.html(res[i].name).val(res[i].id).appendTo($province);
            }
        }
    })

    // 省的下拉列表框添加时间，当省发生变化的时候，切换城市的下拉列表框的内容
    $("#province").change(
        function () {
            var pid = $("#province").val();
            $.ajax({
                type: "post",
                url: "${root}/servlet/order?methodName=listPCDByPid",
                data: {pid: pid},
                dataType: "json",
                success: function (res) {
                    $("#ph").val($("#province option:selected").html());
                    // 获取省的标签对象
                    var $city = $("#city");
                    $city.html("<option value=''>-- 请选择市 --</option>");

                    // 获取区的标签对象
                    var $district = $("#district");
                    $district.html("<option value=''>-- 请选择县(区) --</option>");
                    for (var i = 0; i < res.length; i++) {
                        // 创建下拉列表需要的option标签对象
                        var $option = $("<option></option>");
                        // 将option标签对象添加到下拉列表中
                        $option.html(res[i].name).val(res[i].id).appendTo($city);
                    }
                }
            })
        })

    $("#city").change(function () {
        var pid = $("#city").val();
        $.ajax({
            type: "post",
            async: true,
            url: "${root}/servlet/order?methodName=listPCDByPid",
            data: {pid: pid},
            dataType: "json",
            success: function (res) {
                $("#ch").val($("#city option:selected").html());
                // 获取区的标签对象
                var $district = $("#district");
                $district.html("<option value=''>-- 请选择县(区) --</option>");
                for (var i = 0; i < res.length; i++) {
                    // 创建下拉列表需要的option标签对象
                    var $option = $("<option></option>");
                    // 将option标签对象添加到下拉列表中
                    $option.html(res[i].name).val(res[i].id).appendTo($district);
                }
            }
        })
    })

    $("#district").change(function () {
        //设置区的隐藏域的值为当前区的名称
        $("#dh").val($("#district option:selected").html());
    })

</script>
</html>