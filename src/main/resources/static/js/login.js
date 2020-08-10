$(document).ready(function($){
    $('#loginForm').submit(function (event) {
        event.preventDefault();
        var $form = $(this);

        var $button = $form.find('button');

        $.ajax({
            url: $form.attr('action'),
            type: $form.attr('method'),
            dataType: "json",
            data: $form.serialize(),

            beforeSend: function(xhr, setting){
                $button.attr('disabled', true);
            },
            complete: function(xhr, testStatus){
                $button.attr('disabled', false);
            },

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
                alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error" + error);
            }
        })

    })

})

