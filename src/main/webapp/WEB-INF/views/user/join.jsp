<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp" %>
<style>
    #id_ok{
        color:#008000;
        display: none;
    }

    #id_already{
        color:#6A82FB;
        display: none;
    }
</style>
<div class="container-sm">
    <input type="hidden" id="RSAModulus" name="RSAModulus" value="${publicKeyModulus}" />
    <input type="hidden" id="RSAExponent" name="RSAExponent" value="${publicKeyExponent}" />
    <form action="/user/join" method="post" id="frm">
        <h1 class="h3 mb-3 fw-normal">회원가입</h1>

        <div class="form-floating mb-3">
            <label for="name">이름</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력해주세요" required>
        </div>
        <div class="form-floating mb-3">
            <label for="username">아이디</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="아이디를 입력해주세요" required>
            <br>
            <span id="id_ok">사용 가능한 아이디 입니다.</span>
            <span id="id_already">중복된 아이디 입니다.</span>
        </div>
        <div class="form-floating mb-3">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력해주세요" required>
        </div>
        <div class="form-floating mb-3">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력해주세요" required>
        </div>
    </form>
        <div class="col text-center">
            <button class="btn btn-primary" id="id-check" type="button" @click="idCheck()">아이디 중복확인</button>
            <button class="btn btn-primary" id="btn-join" type="button" @click="join()">회원가입</button>
            <button class="btn btn-secondary" type="button" onclick="history.back()">취소</button>
        </div>
</div>
<%@include file="../common/footer.jsp" %>

