<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改产品</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/product/updateProduct.jhtml" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${product.id }"/>
    <input type="text" name="ids" id="ids">
    <table width="500px" cellspacing="0px" cellpadding="0px" border="1px">
        <tr>
            <td>产品名:</td>
            <td><input type="text" name="productName" value="${product.productName }"/></td>
        </tr>
        <tr>
            <td>图片:</td>
            <td>
                <img src="<%=request.getContextPath()%>${product.productImagePath}" width="100px" height="100px">
                <input type="file" name="productImage"/>
                <input type="hidden" name="productImagePath" value="${product.productImagePath}">
            </td>
        </tr>
        <tr>
            <td>子图:</td>
            <td>
                <div style="width: 550px">
                    <c:forEach items="${productImageList}" var="productImage">
                        <div style="float: left;margin: 5px;" data-flag="image">
                            <div>
                                <img src="<%=request.getContextPath()%>${productImage.imagePath}" width="100px"
                                     height="100px">
                            </div>
                            <div style="text-align: center;">
                                <input type="button" value="删除" onclick="deleteImage(this, '${productImage.id}');"/>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </td>
        </tr>
        <tr data-flag="childImageRow">
            <td>产品子图:</td>
            <td><input type="file" name="productChildImages"/><input value="＋" type="button" onclick="addRow()"></td>
        </tr>
        <tr>
            <td>价格:</td>
            <td><input type="text" name="productPrice" value="${product.productPrice }"/></td>
        </tr>
        <tr>
            <td>品牌列表:</td>
            <td>
                <select name="brand.id" id="brandSelect">
                    <option value="-1">===请选择===</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="更新产品"/></td>
            <td><input type="reset" value="取消"/></td>
        </tr>
    </table>
</form>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/shop/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/shop/brand.js"></script>
<script>
    $(function () {
        initBrandList("brandSelect", '${product.brand.id}');
    })

    function addRow() {
        $("tr[data-flag='childImageRow']").last().after('<tr data-flag="childImageRow"><td>产品子图:</td><td><input type="file" name="productChildImages"/><input value="－" type="button" onclick="removeRow(this)"></td></tr>');
    }

    function deleteImage(obj, imageId) {
        // 删除界面上的图片div
        $(obj).parents("div[data-flag='image']").remove();
        // 记录删除的图片id
        $("#ids").val($("#ids").val()+","+imageId);
    }

    function removeRow(obj) {
        $(obj).parents("tr").remove();
    }

</script>
</body>
</html>