<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPEHTMLPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>

    <title>My JSP 'index.jsp' starting page</title>
</head>

<body>
<h3>这是首页</h3>欢迎
<sec:authentication property ="name"/> !


<a href="admin.jsp">进入admin页面</a>
<a href="other.jsp">进入其它页面</a>
</body>

</html>