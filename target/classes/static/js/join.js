let join = {
    template: `
        <div> component test </div>
    `
    ,
    data() {
        return {
            // emailChk: 0,
        }
    },
    methods: {
        // join : $(function (){
        //     $('#btn-join').click(function (){
        //
        //         if($('#name').val().length === 0) {
        //             alert('이름을 입력하세요');
        //             return false;
        //         } else if ($('#username').val().length === 0) {
        //             alert('아이디를 입력하세요');
        //             return false;
        //         } else if($('#email').val().length === 0) {
        //             alert('이메일을 입력하세요');
        //             return false;
        //         } else if($('#password').val().length === 0) {
        //             alert('비밀번호를 입력하세요');
        //             return false;
        //         }
        //
        //         let data = {
        //             name: $('#name').val(),
        //             username: $('#username').val(),
        //             email: $('#email').val(),
        //             password: $('#password').val(),
        //         };
        //
        //         $.ajax({
        //             type: "POST",
        //             url: "/user/api/join",
        //             data: JSON.stringify(data),
        //             contentType: "application/json; charset=utf-8",
        //             dataType: "json",
        //         }).done(function (res){
        //             console.log(res.toString());
        //             alert('회원가입이 완료되었습니다.');
        //             location.href = "/";
        //         }).fail(function (err) {
        //             alert("회원가입이 실패하였습니다.");
        //             alert(err);
        //         })
        //
        //     })
        // }),

        // idCheck: $(function () {
        //     $('#id-check').click(function () {
        //
        //         let id = $('#username').val();
        //
        //         if(id.length === 0) {
        //             alert('아이디를 입력해주세요');
        //             return false;
        //         }
        //
        //         $.ajax({
        //             async: true,
        //             type: "POST",
        //             url: "/user/api/idCheck",
        //             data: id,
        //             contentType: "application/json; charset=utf-8",
        //             dataType: "json",
        //         }).done(function (count) {
        //             console.log(count);
        //             if(count > 0) {
        //                 $('#id_already').css("display", "inline-block");
        //                 $('#id_ok').css("display", "none");
        //             } else {
        //                 $('#id_ok').css("display", "inline-block");
        //                 $('#id_already').css("display", "none");
        //             }
        //         }).fail(function (err) {
        //             console.log("에러 발생! " + err)
        //         })
        //     })
        // }),

        // login: $(function (){
        //     $('#btn-login').click(function () {
        //
        //         let data = {
        //             username: $('#username').val(),
        //             password: $('#password').val(),
        //         }
        //
        //         if(data.username === null || data.username === "") {
        //             alert('아이디를 입력하세요');
        //             $('#username').focus();
        //             return;
        //         } else if (data.password === null || data.password === "") {
        //             alert('패스워드를 입력하세요');
        //             $('#password').focus();
        //             return;
        //         }
        //
        //         $.ajax({
        //             type: "POST",
        //             url: "/user/api/login",
        //             data: JSON.stringify(data),
        //             contentType: "application/json; charset=utf-8",
        //             // dataType: "json",
        //         }).done(function (res){
        //             if(res === 'success') {
        //                 alert(res.message);
        //                 location.href="/";
        //             } else {
        //                 alert(res.message);
        //                 location.reload();
        //             }
        //         }).fail(function (err){
        //             console.log('알 수 없는 에러가 발생하였습니다. 에러내용 : ' + err);
        //         })
        //     })
        // }),
    }
}

