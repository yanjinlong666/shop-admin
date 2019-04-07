<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加产品</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/product/add.jhtml" method="post" enctype="multipart/form-data">
    <table width="600px" cellpadding="0px" cellspacing="0px" border="1px">
        <tr>
            <td>产品名:</td>
            <td><input type="text" name="productName"/></td>
        </tr>
        <tr>
            <td>图片:</td>
            <td><input type="file" name="productImage"/></td>
        </tr>
        <tr>
            <td>价格:</td>
            <td><input type="text" name="productPrice"/></td>
        </tr>
        <tr>
            <td>品牌列表:</td>
            <td>
                <select name="brand.id" id="brandInfoSelect">
                    <option value="-1">===请选择===</option>
                </select></td>
        </tr>
        <tr data-flag="childImageRow">
            <td>产品子图:</td>
            <td><input type="file" name="productChildImages"/><input value="＋" type="button" onclick="addRow()"></td>
        </tr>
        <tr>
            <td><input type="submit" value="增加产品"/></td>
            <td><input type="reset" value="取消"/></td>
        </tr>
    </table>
</form>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/shop/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/shop/brand.js"></script>
<script>
    $(function () {
        initBrandList("brandInfoSelect");

    })
    
    function addRow() {
        $("tr[data-flag='childImageRow']").last().after('<tr data-flag="childImageRow"><td>产品子图:</td><td><input type="file" name="productChildImages"/><input value="－" type="button" onclick="removeRow(this)"></td></tr>');
    }
    
    function removeRow(obj) {
        $(obj).parents("tr").remove();
    }

</script>
</body>
</html>