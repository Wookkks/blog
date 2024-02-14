<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp"%>
<style>
    #imageData {
        width: 600px;
        height: auto;
        margin-bottom: 5px;
    }
</style>
<div class="container">
    <form id="frm">
        <input type="hidden" id="boardId" value="${board.id}">
        <div class="form-group">
            <label for="category">카테고리</label>
            <select class="form-control" id="category" name="category">
                <option selected>${board.category}</option>
                <option value="카테고리">카테고리</option>
                <option value="일상">일상</option>
                <option value="정보">정보</option>
            </select>
        </div>

        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title" value="${board.title}">
        </div>
        <c:choose>
            <c:when test="${not empty imgName}">
                <div class="form-group">
                    <label for="imageData">첨부된 이미지</label>
                    <img class="form-control-file" id="imageData" name="imageData" alt="첨부된 이미지"
                         src="/images/${imgName}">
                    <button type="button" class="btn btn-outline-primary" id="delete-img" @click="deleteImage()">이미지 삭제</button><br/><br/>
                    <label for="file">새 이미지 첨부</label>
                    <input type="file" class="form-control-file" id="file" name="file">
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group">
                    <label for="file">이미지 첨부</label>
                    <input type="file" class="form-control-file" id="file" name="file">
                </div>
            </c:otherwise>
        </c:choose>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control h-25" id="content" name="content" rows="5">${board.content}</textarea>
        </div>
    </form>
    <div class="row">
        <div class="col text-center">
            <button type="button" class="btn btn-outline-primary" onclick=history.back()>취소</button>
            <button type="button" class="btn btn-outline-primary" id="btn-board-update">수정</button>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/board.js"></script>
<%@include file="../common/footer.jsp"%>
