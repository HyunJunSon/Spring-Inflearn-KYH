<%--
  Created by IntelliJ IDEA.
  User: hyunjunson
  Date: 2024. 11. 21.
  Time: 오전 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="kimyh.servelet.domain.member.MemberRepository" %>
<%@ page import="kimyh.servelet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // request, response 사용 가능
  MemberRepository memberRepository = MemberRepository.getInstance();
  System.out.println("save.jsp");
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));
  Member member = new Member(username, age);
  System.out.println("member = " + member);
  memberRepository.save(member);
%>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
성공

<ul>
  <li>id=<%=member.getId()%></li>
  <li>username=<%=member.getUsername()%></li>
  <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
