<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌管理</title>
</head>
<body>
<form>
	<table>
		<tr>
			<td>品牌名:</td>
			<td><input type="text" id="brandName"/></td>
		</tr>
		<tr>
			<td><input type="button" value="查询"/></td>
			<td><input type="reset" value="重置"/></td>
		</tr>
	</table>
</form>
<div id="brandListDiv">
<jsp:include page="/WEB-INF/view/brand/pagelist.jsp"></jsp:include>
</div>

<input type="text" id="add_brandName"/><input type="button" value="增加" onclick="addBrand();"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
function editBrand(brandId) {
	var brandName = $("td[data-id='"+brandId+"']").attr("data-info");
	$("td[data-id='"+brandId+"']").html("<input type='text' value='"+brandName+"' id='updateBrandName_"+brandId+"'/><input type='button' value='保存' onclick='saveBrand(\""+brandId+"\")'/><input type='button' value='取消' onclick='cancel(\""+brandId+"\", \""+brandName+"\");'/>");
}


function cancel(brandId, brandName) {
	$("td[data-id='"+brandId+"']").html(brandName);
}


function saveBrand(brandId) {
	var v_brandName = $("#updateBrandName_"+brandId).val();
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/brand/updateBrand.jhtml",
		data:{"id":brandId, "brandName":v_brandName},
		success:function(result) {
			if (result.code == 200) {
				$("td[data-id='"+brandId+"']").html(v_brandName);
				$("td[data-id='"+brandId+"']").attr("data-info", v_brandName);
				//search();
			}
		}
	})
}


function addBrand() {
	var v_brandName = $("#add_brandName").val();
	// 插入数据库
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/brand/addBrand.jhtml",
		data:{"brandName":v_brandName},
		success:function(result) {
			if (result.code == 200) {
				// 清空
				$("#add_brandName").val("");
				// 刷新页面
				search();
			} else {
				alert(result.message);
			}
		}
	})	
	
}

function search() {
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/brand/findList.jhtml",
		data:{"flag":1},
		success:function(result) {
			$("#brandListDiv").html(result);
		}
	})
}
</script>
</body>
</html>