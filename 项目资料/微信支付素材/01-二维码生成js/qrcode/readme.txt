1  引入所需的js插件

<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- 弹出层插件 -->
<script type="text/javascript" src="js/jquery.wxdialog.js"></script>
<script type="text/javascript" src="js/qrcode.js"></script>
<!-- 二维码中间的logo -->
<script type="text/javascript" src="js/qrcode_logo.js"></script>

2  调用弹出二维码窗口的js函数

// 弹出扫码支付窗口，code_url,为微信预支付生成的地址。
$.WXDialog(code_url);

3  即可看到二维码图片