<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2023-12-29
  Time: 오전 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp" %>

<div class="container">
    <input type="hidden" id="RSAModulus" name="RSAModulus" value="${publicKeyModulus}" />
    <input type="hidden" id="RSAExponent" name="RSAExponent" value="${publicKeyExponent}" />
    <form>
        <div class="mb-3">
            <label class="form-label" for="username">아이디</label>
            <input class="form-control" type="text" name="username" id="username" autocomplete="off"/>
        </div>
        <div class="mb-3">
            <label class="form-label" for="password">비밀번호</label>
            <input class="form-control" type="password" name="password" id="password" autocomplete="off"/>
        </div>
    </form>
    <p class="msg" id="capslockMsg" style="display: none;">CapsLock이 켜져 있습니다.</p>

    <div class="col text-center">
        <button class="btn btn-primary" id="btn-login" type="button">로그인</button>
        <button class="btn btn-secondary" type="button" onclick="history.back()">취소</button>
    </div>
</div>
<%@include file="../common/footer.jsp" %>