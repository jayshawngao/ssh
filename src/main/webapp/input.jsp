<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		$(":input[name=lastName]").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			var $this = $(this);
			
			if(val != ""){
				//把当前节点后面的所有 font 兄弟节点删除
				$this.nextAll("font").remove();
				
				var url = "emp-validateLastName";
				var args = {"lastName":val, "time":new Date()};
				$.post(url, args, function(data){
					//表示可用
					if(data == "1"){
						$this.after("<font color='green'>LastName可用!</font>");
					}
					//不可用
					else if(data == "0"){
						$this.after("<font color='red'>LastName不可用!</font>");						
					}
					//服务器错误
					else{
						alert("服务器错误!");
					} 
				});
			}else{
				alert("lastName 不能为空");
				$(this).val("");
				$this.focus();
			}
		});
	})
	
</script>
</head>
<body>

	<h4>Edit Employee</h4>SS
	<s:form action="emp-save">S
		<s:if test="id != null">
			<s:textfield name="lastName" label="LASTNAME" disabled="true"></s:textfield>
			<s:hidden name="id"></s:hidden>
		</s:if>
		<s:else>
		<s:textfield name="lastName" label="LASTNAME"></s:textfield>
		</s:else>
		<s:textfield name="email" label="EMAIL"></s:textfield>
		<s:textfield name="birth" label="BIRTH"></s:textfield>
		<s:select list="#request.departments" name="department.id" listKey="id" listValue="departmentName" label="DEPARTMENT"></s:select>	
		<s:submit></s:submit>
	</s:form>
</body>
</html>