$(document.readyState, function (){

})
let board = {
    template: ``,
    data: {},
    methods: {
        write: $(function() {
            $('#btn-write').on('click', function (){

                let data = {
                    userId: $('#userId').val(),
                    category: $('#category option:selected').val(),
                    title: $('#title').val(),
                    content: $('#content').val(),
                }

                let frm = $('#frm')[0];
                let formData = new FormData(frm);

                if($('#file')[0].files.length > 0) {
                    formData.append('file', $('#file')[0].files[0]);
                }

                formData.append('boardDTO', new Blob([JSON.stringify(data)], {type: 'application/json'}));

                $.ajax({
                    type: 'POST',
                    url: '/board/api/write',
                    data: formData,
                    contentType: false,
                    processData: false,
                }).done(function (res){
                    if(res.code === "success") {
                        if(confirm('글을 작성하시겠습니까?')){
                            alert(res.message);
                            location.href='/board';
                        }
                    } else {
                        alert(res.message);
                        location.reload();
                    }
                }).fail(function (err){
                    alert('오류가 발생하였습니다.');
                    console.log(err);
                })
            })
        }),
        replyWrite: $(function (){
            $('#btn-reply-save').on('click', function (){
                let data = {
                boardId: $('#boardId').val(),
                userId: $('#userId').val(),
                content: $('#reply-content').val()
                };

                if(confirm('댓글을 작성하시겠습니까?')){
                    $.ajax({
                    type: 'post',
                    url: '/board/api/replyWrite',
                    data: JSON.stringify(data),
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    }).done(function (res){
                        if(res.code === 'success'){
                            alert(res.message);
                            location.reload();
                        }
                    }).fail(function (err){
                        alert('에러가 발생하였습니다. 에러내용 : ', err);
                    })
                }
            })
        }),
        replyDelete: $(function (){
           $('.btn-reply-delete').on('click', function (){
               let replyId = $('#replyId').val();

               if(confirm('댓글을 삭제하시겠습니까?')) {
                   $.ajax({
                       type: 'delete',
                       url: '/board/api/replyDelete/' + replyId,
                       dataType: 'json'
                   }).done(function (res){
                       if(res.code === 'success') {
                           alert(res.message);
                           location.reload();
                       }
                   }).fail(function (err){
                       alert('에러가 발생하였습니다 : ' + err);
                   })
               }
           })
        }),

        update: $(function (){
            $('#btn-board-update').on('click', function (){

                let boardId = $('#boardId').val();

                let data = {
                    category: $('#category option:selected').val(),
                    title: $('#title').val(),
                    content: $('#content').val(),
                }

                let frm = $('#frm')[0];
                let deleteImgBtn = $('#delete-img')[0];
                let formData = new FormData(frm);

                if(formData.has(deleteImgBtn)) {
                    formData.delete(deleteImgBtn);
                }

                if($('#file')[0].files.length > 0) {
                    formData.append('file', $('#file')[0].files[0]);
                }

                formData.append('boardDTO', new Blob([JSON.stringify(data)], {type: 'application/json'}));

                $.ajax({
                    type: 'PUT',
                    url: '/board/api/' + boardId,
                    data: formData,
                    contentType: false,
                    processData: false,
                }).done(function (res){
                    if(res.code === 'success'){
                        if(confirm('글을 수정하시겠습니까?')){
                            alert(res.message);
                            console.log(res);
                            location.href='/board';
                        }
                    }
                }).fail(function (err){
                    alert('오류가 발생하였습니다.');
                    console.log(err);
                })
            })
        }),

        delete: $(function (){
            $('.btn-delete').on('click', function (){
                let boardId = $('#boardId').text();
                if(confirm('삭제하시겠습니까?')){
                    $.ajax({
                        type: 'delete',
                        url: '/board/api/' + boardId,
                        dataType: 'json'
                    }).done(function (res){
                        if(res.code === 'success') {
                            alert(res.message);
                            location.href='/board';
                        }
                    }).fail(function (err){
                        alert('에러 발생 : ' + err);
                    })
                }
            })
        }),

        deleteImage: $(function (){
            $('#delete-img').on('click', function (){
                if(confirm('이미지를 삭제하시겠습니까?')){
                    let boardId = $('#boardId').val();

                    $.ajax({
                        type: 'post',
                        url: '/board/api/deleteImg/' + boardId,
                        dataType: 'json',
                    }).done(function (res){
                        if(res.code === 'success') alert(res.message);
                        location.reload();
                    }).fail(function (err){

                    })
                }
            })
        })
    }
}