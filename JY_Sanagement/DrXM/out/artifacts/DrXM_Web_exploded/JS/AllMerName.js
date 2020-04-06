$.ajax({
    url:"merallname",
    dataType:"text",
    success :function (data) {
        if (data=="no"){
            location.href="login_signup.html"
        } else {
            $("#merallusername").append(data);
        }
    }
})