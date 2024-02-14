<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp" %>

<div class="container-sm">
    <form action="#" method="post" id="frm">
        <h1 class="h3 mb-3 fw-normal">회원정보</h1>
        <input type="hidden" id="id" value="${userId}">

        <div class="form-floating mb-3">
            <label for="name">이름</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="이름을 입력해주세요" required>
        </div>
        <div class="form-floating mb-3">
            <label for="username">아이디</label>
            <input type="text" class="form-control" id="username" name="username" value="${user.username}" placeholder="아이디를 입력해주세요" disabled>
        </div>
        <div class="form-floating mb-3">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="이메일을 입력해주세요" required>
        </div>
        <div class="form-floating mb-3">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" name="password" value="${user.password}" placeholder="비밀번호를 입력해주세요" required>
        </div>
    </form>
        <div class="col text-center">
            <button class="btn btn-primary" id="btn-update" type="button">회원정보 수정</button>
            <button class="btn btn-secondary" type="button" onclick="history.back()">취소</button>
            <button class="btn btn-primary" id="withdraw" type="button" style="float:right;">회원탈퇴</button>
        </div>
</div>
<%@include file="../common/footer.jsp" %>

