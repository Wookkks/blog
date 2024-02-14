<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <title>Blog</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <%-- RSA --%>
    <script type="text/javascript" src="/rsa/rsa.js"></script>
    <script type="text/javascript" src="/rsa/jsbn.js"></script>
    <script type="text/javascript" src="/rsa/prng4.js"></script>
    <script type="text/javascript" src="/rsa/rng.js"></script>
</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">

<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-between justify-content-md-between py-3 mb-4 border-bottom">
        <c:choose>
            <c:when test="${not empty sessionScope.userId}">
                <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/" class="nav-link px-2 link-secondary">홈</a></li>
                    <li><a href="/board/write" class="nav-link px-2">글쓰기</a></li>
                    <li><a href="/board" class="nav-link px-2">글목록</a></li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/" class="nav-link px-2 link-secondary">홈</a></li>
                    <li><a href="/board" class="nav-link px-2">글목록</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${not empty sessionScope.userId}">
                <div class="col-md-3 text-end">
                    <button type="button" class="btn btn-primary" onclick="location.href='/user/detail/${sessionScope.userId}'">회원정보</button>
                    <button type="button" id="btn-logout" class="btn btn-outline-primary me-2">로그아웃</button>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-md-3 text-end">
                    <button type="button" class="btn btn-primary" onclick="location.href='/user/join'">회원가입</button>
                    <button type="button" class="btn btn-outline-primary me-2" onclick="location.href='/user/login'">로그인</button>
                </div>
            </c:otherwise>
        </c:choose>
    </header>
</div>