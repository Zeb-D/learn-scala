<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPEhtmlPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action ="j_spring_security_check" method="POST">
    <table>
        <tr>
            <td>用户:</td>
            <td><input type ='text' name='j_username'></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type ='password' name='j_password'></td>
        </tr>
        <tr>
            <td><input name ="reset" type="reset"></td>
            <td><input name ="submit" type="submit"></td>
        </tr>
    </table>
</form>
</body>
</html>