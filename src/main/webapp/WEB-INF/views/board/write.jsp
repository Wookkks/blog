<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp"%>
<div class="container">
    <form id="frm">
        <input type="hidden" id="userId" value="${userId}">
        <div class="form-group">
            <label for="category">카테고리</label>
            <select class="form-control" id="category" name="category" required>
                <option value="카테고리">카테고리</option>
                <option value="일상">일상</option>
                <option value="정보">정보</option>
            </select>
        </div>

        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>

        <div class="form-group">
            <label for="file">이미지 첨부</label>
            <input type="file" class="form-control-file" id="file" name="file">
        </div>

        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control h-25" id="content" name="content" rows="5" required></textarea>
        </div>
    </form>
    <div class="row">
        <div class="col text-center">
            <button type="button" class="btn btn-outline-primary" onclick=location.href='/board'>취소</button>
            <button type="button" id="btn-write" class="btn btn-primary me-2" @click="write()">글쓰기</button>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp"%>
<script type="text/javascript" src="/js/board.js"></script>
