<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table id="main-container">
		<tr>
			<td class="content center">
				<form id="login_form" action="controller" method="post">
					<input type="hidden" name="command" value="login" />

					<fieldset>
						<legend>Login</legend>
						<input type="text" name="login" /><br />
					</fieldset>
					<br />
					<fieldset>
						<legend>Password</legend>
						<input type="password" name="password" />
					</fieldset>
					<br /> <input type="submit" value="Login">
				</form>
			</td>
		</tr>
	</table>

</body>
</html>