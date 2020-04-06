function delname() {
    $.ajax({
        url:"delallname",
        dataType:"text",
        success :function (data) {
            if(data=="sucess"
            ){location.href="login_signup.html"}
        }
    })
}