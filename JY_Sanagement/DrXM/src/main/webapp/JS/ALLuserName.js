$.ajax({
    url:"allname",
    dataType:"text",
    success :function (data) {

        if (data=="no"){
            location.href="login_signup.html"
        } else {
            $("#allusername").append(data);
        }
    }
})