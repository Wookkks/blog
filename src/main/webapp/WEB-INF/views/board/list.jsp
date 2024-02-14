<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp"%>
<style>
    #img {
        width: 23px;
        height: 18px;
        margin-left: 5px;
        background-color: transparent;
    }
</style>
<div class="container">
    <div>
        <table class="table table-striped" style="text-align: center">
            <thead>
            <tr>
                <th scope="col" width="90px">글번호</th>
                <th scope="col" width="120px">카테고리</th>
                <th scope="col" width="220px">제목</th>
                <th scope="col" width="120px">작성자</th>
                <th scope="col" width="210px">최초 작성일</th>
                <th scope="col" width="210px">최종 수정일</th>
                <th scope="col" width="100px">조회수</th>
                <th scope="col" width="80px"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <th scope="row"><span id="boardId">${board.id}</span></th>
                    <c:choose>
                        <c:when test="${board.category == '카테고리'}">
                            <td><span> <b>-</b> </span></td>
                        </c:when>
                        <c:otherwise>
                            <td><span>${board.category}</span></td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <div style="display: flex; align-items: center;">
                        <a href="/board/${board.id}" style="display: block; text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width: 220px;">
                            <span >${board.title}</span>
                        </a>
                            <c:if test="${not empty board.fileName}">
                                <img src="/img/img_icon_2.png" id="img" alt="이미지있음"/>
                            </c:if>
                        </div>
                    </td>
<%--                    <td>${board.writer}</td>--%>
                    <td><span><c:out value="${fn:substring(board.writer, 0, board.writer.length()/2)}"/>*<c:out value="${fn:substring(board.writer, (board.writer.length()/2)+1, fn:length(board.writer))}"/></span></td>
                    <td><span><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm" /></span></td>
                    <td><span><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm" /></span></td>
                    <td><span>${board.count}</span></td>
                    <td>
                        <c:if test="${userId == board.userId}">
                            <button class="btn btn-outline-primary btn-sm btn-delete" @click="delete()">삭제</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item <c:if test="${paging.currentPage == 1}">disabled</c:if>">
                <a class="page-link" href="?currentPage=${paging.startPage}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}" step="1">
                <li class="page-item"><a class="page-link" href="?currentPage=${num}">${num}</a></li>
            </c:forEach>
            <li class="page-item <c:if test="${paging.endPage == paging.currentPage}">disabled</c:if>">
                <a class="page-link" href="?currentPage=${paging.endPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
<%@include file="../common/footer.jsp"%>
<script type="text/javascript" src="/js/board.js"></script>
