<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp"%>
<style>
    #imageData {
        width: 600px;
        height: auto;
    }
</style>
<div class="container">
    <form id="frm">

        <div class="form-group">
            <label for="category">카테고리</label>
            <select class="form-control" id="category" name="category" disabled>
                <option selected>${board.category}</option>
            </select>
        </div>

        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title" value="${board.title}" disabled>
        </div>
        <c:if test="${not empty imgName}">
            <div class="form-group">
                <label for="imageData">첨부된 이미지</label>
                <img class="form-control-file" id="imageData" name="imageData" alt="첨부된 이미지"
                     src="/images/${imgName}">
            </div>
        </c:if>

        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control h-25" id="content" name="content" rows="5" disabled>${board.content}</textarea>
        </div>
    </form>
    <div class="row" style="margin-bottom: 10px;">
        <div class="col text-center">
            <button type="button" class="btn btn-outline-primary" onclick=history.back()>이전</button>
            <c:if test="${userId == board.userId}">
            <button type="button" class="btn btn-outline-primary" onclick=location.href='/board/update/${board.id}'>수정</button>
            </c:if>
        </div>
    </div>
    <div class="card">
        <form>
            <input type="hidden" id="boardId" value="${board.id}">
            <input type="hidden" id="userId" value="${userId}">
            <div class="card-body">
                <textarea id="reply-content" class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply-box" class="list-group">
            <c:forEach var="reply" items="${replyList}">
                <li id=" " class="list-group-item d-flex justify-content-between">
                    <input type="hidden" id="replyId" value="${reply.replyId}">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic" style="margin-right: 10px;">작성자 : ${reply.name}</div>
                        <c:if test="${reply.userId == userId}">
                            <button onclick="location.href='#'" class="badge btn-reply-delete">삭제</button>
                        </c:if>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<script type="text/javascript" src="/js/board.js"></script>
<%@include file="../common/footer.jsp"%>
