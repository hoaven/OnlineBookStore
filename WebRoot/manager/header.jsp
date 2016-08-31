<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.itcast.cn/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
  </head>
  
<body style="text-align: center;">
  	<br />
<br />
<script language="javascript">
	function qiehuan(num){
		for(var id = 0;id<=9;id++)
		{
			if(id==num)
			{
				document.getElementById("qh_con"+id).style.display="block";
				document.getElementById("mynav"+id).className="nav_on";
			}
			else
			{
				document.getElementById("qh_con"+id).style.display="none";
				document.getElementById("mynav"+id).className="";
			}
		}
	}
</script>
<div id=menu_out>
  <div id=menu_in>
    <div id=menu>
      <UL id=nav>
        <LI><A class=nav_on id=mynav0 onmouseover=javascript:qiehuan(0) href="#"><SPAN>分类管理</SPAN></A></LI>
        <LI class="menu_line"></LI>
        <li><a href="#" onmouseover="javascript:qiehuan(1)" id="mynav1" class="nav_off"><span>图书管理</span></a></li>
        <li class="menu_line"></li>
        <li><a href="#" onmouseover="javascript:qiehuan(2)" id="mynav2" class="nav_off"><span>订单管理</span></a></li>
        <li class="menu_line"></li>
        
        
      </UL>
      <div id=menu_con>
        <div id=qh_con0 style="DISPLAY: block">   <!-- 默认不显示 -->
          <UL>
            <LI><a href="${pageContext.request.contextPath}/manager/addCategory.jsp"><span>添加分类</span></A></LI>
            <LI class=menu_line2></LI>
            <LI><A href="${pageContext.request.contextPath}/servlet/ManagerServlet?operation=showAllCatetory"><SPAN>查询分类</SPAN></A></LI>
            <LI class=menu_line2></LI>
           
          </UL>
        </div>
        <div id=qh_con1 style="DISPLAY: none">
          <UL>
            <LI><a href="${pageContext.request.contextPath}/servlet/ManagerServlet?operation=showAllCatetoryUI"><span>添加图书</span></A></LI>
            <LI class=menu_line2></LI>
            <LI><A href="${pageContext.request.contextPath}/servlet/ManagerServlet?operation=showAllBook"><SPAN>查询图书</SPAN></A></LI>
            <LI class=menu_line2></LI>
           
          </UL>
        </div>
        <div id=qh_con2 style="DISPLAY: none">
          <UL>
            <LI><a href="#"><span>待处理订单</span></A></LI>
            <LI class=menu_line2></LI>
            <LI><A href="#"><SPAN>已处理订单</SPAN></A></LI>
            <LI class=menu_line2></LI>
           
          </UL>
        </div>
        
      </div>
    </div>
  </div>
</div>

