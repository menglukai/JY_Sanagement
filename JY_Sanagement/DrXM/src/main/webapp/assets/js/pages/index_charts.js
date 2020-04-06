function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    var q = window.location.pathname.substr(1).match(reg_rewrite);
    if (r != null) {
        return unescape(r[2]);
    } else if (q != null) {
        return unescape(q[2]);
    } else {
        return null;
    }
}

<!-- 调用函数  -->
var queryString = getQueryString("Info");

<!-- 字符串转JSON  -->
var parse = JSON.parse(queryString);
if (parse == null) {
    alert("登录失效，请登录");
    window.location = "login_signup_merchant.html"
}
;
<!-- 插入数据  -->
$("#pointer1").append(parse.today_order);
$("#pointer2").append(parse.sum_orderchant);
$("#pointer3").append(parse.sum_Yesydayincomeincome);
$("#info1").append("欢迎您:" + parse.Username);
$("#li0").append("<a href=\"show?Username=" + parse.Username + "\"><i class=\"ion-ios-speedometer-outline\"></i> 首页</a>");
$("#li1").append("<a href=\"/DR/selectSame?Username=" + parse.Username + "\"> 门店列表</a>");
$("#li2").append("<a href=\"staff_info?Username=" + parse.Username + "\"> 员工列表</a>");
$("#li3").append("<a href=\"goolds_info?Username=" + parse.Username + "\"> 商品管理</a>");
$("#li4").append("<a href=\"member_info?Username=" + parse.Username + "\"> 会员列表</a>");
$("#li5").append("<a href=\"membercard_info?Username=" + parse.Username + "\"> 会员等级</a>");
$("#li6").append("<a href=\"appInfo?Username=" + parse.Username + "\"> 产品中心</a>");
$("#li7").append("<a href=\"publicInfo?Username=" + parse.Username + "\"> 公众号管理</a>");
$("#li8").append("<a href=\"info?Username=" + parse.Username + "\">个人信息</a>");