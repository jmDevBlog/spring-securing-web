$(function () {
    $("#btn-login").click(function () {
        login();
    })
})

function login() {
    $.ajax({
        url: "/login",
        type: "POST",
        dataType: "json",
        data: {
            username: $("#username").val(),
            password: $("#password").val()
        },
        success: function (response) {
            if (response.code == "200") {
                // 정상 처리 된 경우
                window.location = response.item.url;	//이전페이지로 돌아가기
            } else {
                alert(response.message);
            }
        },
        error: function (a, b, c) {
            console.log(a, b, c);
        }
    })
}
