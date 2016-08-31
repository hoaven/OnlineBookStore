<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/client/header.jsp"%>
	<h1>您购买的商品如下</h1>
    <c:if test="${empty sessionScope.cart.items}">
    	对不起！您没有购买任何的商品
    </c:if>
    <c:if test="${!empty sessionScope.cart.items}">
    	<table border="1" width="880">
    		<tr>
    			<th>书名</th>
    			<th>作者</th>
    			<th>单价</th>
    			<th>数量</th>
    			<th>小计</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${sessionScope.cart.items}" var="me">  <!-- me是map.entry -->
    			<tr>
	    			<td>${me.value.book.name}</td>
	    			<td>${me.value.book.author}</td>
	    			<td>${me.value.book.price}</td>
	    			<td>${me.value.num}</td>
	    			<td>${me.value.price}</td>
	    			<td>
	    				<a href="#">删除</a>
	    			</td>
	    		</tr>
    		</c:forEach>
    		<tr>
    			<td colspan="6" align="right">
    				总数量：${sessionScope.cart.num}&nbsp;&nbsp;
    				付款金额:${sessionScope.cart.price}&nbsp;&nbsp;
    				<a href="${pageContext.request.contextPath}/servlet/ClientServlet?operation=genOrders">生成订单</a>
    			</td>
    		</tr>
    	</table>
    </c:if>
  </body>
</html>
