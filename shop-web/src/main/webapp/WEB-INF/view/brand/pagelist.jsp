<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty brandList}">
<table border="1px" width="700px" cellpadding="0px" cellspacing="0px" id="productTable">
<thead>
<tr>
	<th>编号</th>
	<th>品牌名</th>
	<th>操作</th>
</tr>
</thead>
<tbody>
<c:forEach items="${brandList}" var="brand">
<tr>
	<td>${brand.id }</td>
	<td data-id="${brand.id }" data-info="${brand.brandName }">${brand.brandName }</td>
	<td>
	<input type="button" value="删除" />
	<input type="button" value="修改" onclick="editBrand('${brand.id }');"/>
	</td>
</tr>
</c:forEach>
</tbody>
</table>
<jsp:include page="/WEB-INF/common/ajaxpage.jsp"></jsp:include>
</c:if>
<c:if test="${empty brandList}">
<h2><font color="red">对不起，没有符合条件的数据！！！</font></h2>
</c:if>