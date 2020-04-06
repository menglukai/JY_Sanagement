function delmername() {
    $.ajax({
        url:"delallmername",
        dataType:"text",
        success :function (data) {
            if(data=="sucess"
            ){location.href="login_signup_merchant.html"}
        }
    })
}