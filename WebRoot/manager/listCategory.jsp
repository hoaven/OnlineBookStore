<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manager/header.jsp"%>
    <h4>分类列表</h4>
    <c:if test="${empty cs}">
    	没有分类，请先添加
    </c:if>
    <c:if test="${!empty cs}">
    	<table border="1" width="90%">
    		<tr>
    			<th>
    			选择
    			</th>
    			<th>分类名称</th>
    			<th>描述</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${cs}" var="c">
    			<tr>
	    			<td>
	    			<input type="checkbox" name="ids" value="${c.id}"/>
	    			</td>
	    			<td>${c.name}</td>
	    			<td>${c.description}</td>
	    			<td>
	    				<a href="#">修改</a>
	    				<a href="#">删除</a>
	    			</td>
	    		</tr>
    		</c:forEach>
    	</table>
    </c:if>
  </body>
</html>
