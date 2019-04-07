function initBrandList(eleId, brandId) {
	$.ajax({
		type:"post",
		url:contextPath+"/brand/list.jhtml",
		success:function(result) {
			if (result.code == 200) {
				var v_brandArr = result.data;
				
				for (var i = 0; i < v_brandArr.length; i++) {
					// 向指定元素里面追加
					$("#"+eleId).append("<option value='"+v_brandArr[i].id+"'>"+v_brandArr[i].brandName+"</option>");
				}
			}
			if (brandId) {
				// 回填了
				$("#"+eleId).val(brandId);
			}
		}
	})
}