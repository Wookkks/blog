<%@ page import="com.example.seo.blog.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/header.jsp"%>
<script>
    let url = "${pageContext.request.contextPath}"
    console.log(url);
</script>
<div id="indexPage" class="container">
    <h2 style="text-align: center">Welcome to Blog Page</h2>
    <p><b>회원 리스트</b></p>
    <div>
        <table class="table table-striped" style="text-align: center">
            <thead>
            <tr>
                <th scope="col" width="120px">회원번호</th>
                <th scope="col" width="580px">이메일</th>
                <th scope="col" width="150px">이름</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="user" items="${userList}">
            <tr>
                <th scope="row"><span>${user.id}</span></th>
<%--                <td><span>${user.email}</span></td>--%>
                <td><span><c:out value="${fn:substring(user.email, 0, user.email.lastIndexOf('@')-3)}"/>***<c:out value="${fn:substring(user.email, user.email.lastIndexOf('@'), fn:length(user.email))}"/></span></td>
<%--                <td><span>${user.name}</span></td>--%>
                <td><span><c:out value="${fn:substring(user.name, 0, user.name.length()/2)}"/>*<c:out value="${fn:substring(user.name, (user.name.length()/2)+1, fn:length(user.name))}"/></span></td>
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

<%@include file="common/footer.jsp"%>

