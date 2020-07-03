<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/phonebook3/phone/update" method="get">
	이름 : <input type="text" name="name" value="${pVo.name}"> <br>
	핸드폰 : <input type="text" name="hp" value="${pVo.hp}"> <br>
	회사 : <input type="text" name="company" value="${pVo.company}"> <br>
	<input type="hidden" name="personId" value="${pVo.personId}"> <br>
	<input type="hidden" name="action" value="update"> <br>
	<button type="submit">수정</button>
	</form>
</body>
</html>