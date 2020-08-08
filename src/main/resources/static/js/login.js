function onEnterLogin() {
    var keyCode = window.event.keyCode;

    if (keyCode == 13) {
        login();
    }
}

function login() {
    $.ajax({
        url: "/login",
        type: "POST",
        dataType: "json",
        data: $('#loginForm').serialize(),
        success: function (response) {
            if (response.code == "200") {
                // 정상 처리 된 경우
                window.location = response.item.url;	//이전페이지로 돌아가기
            } else {
                alert(response.message);
            }
        },
        error: function (request, status, error) {
            console.log(request, status, error);
        }
    })
}
