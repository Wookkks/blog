$(document.readyState, function () {

    function encryptRSA(plain) {
        let rsa = new RSAKey()
            , rsaPublicKeyModulus = $('#RSAModulus').val()
            , rsaPublicKeyExponent = $('#RSAExponent').val();

        rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);
        return rsa.encrypt(plain);
    }

    $('#id-check').click(function () {
        let id = $('#username').val();

        if(id.length === 0) {
            alert('아이디를 입력해주세요');
            return false;
        }

        $.ajax({
            async: true,
            type: "POST",
            url: "/user/api/idCheck",
            data: id,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (count) {
            if(count > 0) {
                $('#id_already').css("display", "inline-block");
                $('#id_ok').css("display", "none");
            } else {
                $('#id_ok').css("display", "inline-block");
                $('#id_already').css("display", "none");
            }
        }).fail(function (err) {
            console.log("에러 발생! " + err)
        })
    })

    $('#btn-join').click(function () {

        if ($('#name').val().length === 0) {
            alert('이름을 입력하세요');
            $('#name').focus();
            return false;
        } else if ($('#username').val().length === 0) {
            alert('아이디를 입력하세요');
            $('#username').focus();
            return false;
        } else if ($('#email').val().length === 0) {
            alert('이메일을 입력하세요');
            $('#email').focus();
            return false;
        } else if ($('#password').val().length === 0) {
            alert('비밀번호를 입력하세요');
            $('#password').focus();
            return false;
        }

        if($('#id_ok').css('display') !== 'inline-block') {
            alert('아이디 중복확인을 해주세요.');
            $('#username').focus();
            return false;
        }

        let password = $('#password').val();
        let encryptedPassword = encryptRSA(password);

        let data = {
            name: $('#name').val(),
            username: $('#username').val(),
            email: $('#email').val(),
            password: encryptedPassword,
        }

        // let data = {
        //     name: $('#name').val(),
        //     username: $('#username').val(),
        //     email: $('#email').val(),
        //     password: $('#password').val(),
        // };

        console.log(data);

        $.ajax({
            type: "POST",
            url: "/user/api/join",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (res) {
            if(res.code === 'success'){
                alert('회원가입이 완료되었습니다.');
                location.href = "/";
            } else {
                alert('오류가 발생하였습니다.');
            }
        }).fail(function (err) {
            alert("회원가입이 실패하였습니다.");
            alert(err);
        })

    })

    $('#btn-login').click(function () {

        if ($('#username').val() === null || $('#username').val() === "") {
            alert('아이디를 입력하세요');
            $('#username').focus();
            return;
        } else if ($('#password').val() === null || $('#password').val() === "") {
            alert('패스워드를 입력하세요');
            $('#password').focus();
            return;
        }
        let username = $('#username').val();
        let password = $('#password').val();

        let encryptedUsername = encryptRSA(username);
        let encryptedPassword = encryptRSA(password);

        let data = {
            username: encryptedUsername,
            password: encryptedPassword,
        }

        console.log(data.username);
        console.log(data.password);

        $.ajax({
            type: "POST",
            url: "/user/api/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (res) {
            if (res.code === "success") {
                alert(res.message);
                location.href = "/";
            } else {
                alert(res.message);
                location.reload();
            }
        }).fail(function (err) {
            console.log('알 수 없는 에러가 발생하였습니다. 에러내용 : ' + err);
        })
    })

    $('#btn-update').on('click', function (){
        console.log('common.js 업데이트?')
        let id = $('#id').val();

        let data = {
            name: $('#name').val(),
            email: $('#email').val(),
            password: $('#password').val(),
        }

        $.ajax({
            type: "PUT",
            url: "/user/api/update/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (res){
            if(res.code === 'success') {
                alert(res.message);
                location.href="/";
            } else {
                alert(res.message);
            }
        }).fail(function (err){
            console.log('에러발생', err)
        })
    })

    $('#btn-logout').on('click', function (){
        if(confirm("로그아웃 하시겠습니까?")) {
            location.href="/user/logout";
            alert("로그아웃 되었습니다.")
        }
    })

    $('#withdraw').on('click', function (){
        if(confirm('회원 탈퇴를 하시겠습니까?')) {
            let userId = $('#id').val();
            console.log(userId);

            $.ajax({
                type: 'delete',
                url: '/user/api/withdraw/' + userId,
                dataType: 'json'
            }).done(function (res){
                console.log(res);
                if(res.code === 'success'){
                    console.log('done 실행됨');
                    alert(res.message);
                    location.href = '/user/logout';
                } else {
                    alert(res.message);
                }
            }).fail(function (err){
                alert('에러 발생 : ' + err);
            })
        }
    })

    function ajaxRequestApi(type, url, datatype, message) {
        $.ajax({
            type: type,
            url: url,
            datatype: datatype,
        }).done(function (res){
            if(res.code === 'success'){
                alert(message);
            } else {
                alert('오류가 발생하였습니다.');
            }
        }).fail(function (err){
            alert(err.toString());
        })

    }
})