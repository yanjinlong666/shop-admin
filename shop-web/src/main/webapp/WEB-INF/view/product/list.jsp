<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/27 0027
  Time: 下午 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="/WEB-INF/common/head.jsp"/>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <a class="navbar-brand" href="#">欢迎${user.realName}登陆,您上次登录的时间<fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>,今天是第${user.loginCount}次登录</a>

        </div>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="/user/logout.jhtml">退出</a></li>
        </ul>

        <!-- Collect the nav links, forms, and other content for toggling -->

    </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    部门管理
                    <button type="button" class="btn btn-success" onclick="showAddDialog()"><span
                            class="glyphicon glyphicon-plus"></span>新增
                    </button>
                    <button type="button" class="btn btn-warning" onclick="showEditDialog()"><span class="glyphicon glyphicon-pencil"></span>修改
                    </button>
                    <button type="button" class="btn btn-danger" onclick="deleteDept();"><span class="glyphicon glyphicon-trash"></span>删除
                    </button>

                </div>
                <div class="panel-body">
                    <ul id="deptTree" class="ztree"></ul>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-primary">
                <div class="panel-heading">商品查询</div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="productName" class="col-md-2 control-label">产品名</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="productName" placeholder="产品名...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">价格</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="minPrice" placeholder="最小价格">
                                    <span class="input-group-addon">
                    <i class="glyphicon glyphicon-yen"></i>
                </span>
                                    <input type="text" class="form-control" id="maxPrice" placeholder="最大价格">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">录入时间</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="minInsertTime" placeholder="最小时间">
                                    <span class="input-group-addon">
                    <i class="glyphicon glyphicon-time"></i>
                </span>
                                    <input type="text" class="form-control" id="maxInsertTime" placeholder="最大时间">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productName" class="col-md-2 control-label">品牌名</label>
                            <div class="col-md-4">
                                <select id="brandSelect" class="form-control">
                                    <option value="-1">===请选择===</option>
                                </select>
                            </div>
                        </div>

                        <div style="text-align: center;">
                            <button type="button" class="btn btn-primary" onclick="search()"><span
                                    class="glyphicon glyphicon-ok"></span>提交
                            </button>
                            <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-refresh"></span>重置
                            </button>
                        </div>
                    </form>
                </div>


            </div>
            <div class="panel panel-default">
                <div class="panel-heading">商品列表</div>
                <table id="productTable" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>产品名</th>
                        <th>产品图片</th>
                        <th>价格</th>
                        <th>品牌名</th>
                        <th>录入时间</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>选择</th>
                        <th>产品名</th>
                        <th>产品图片</th>
                        <th>价格</th>
                        <th>品牌名</th>
                        <th>录入时间</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>

    </div>

</div>

<div id="addDeptDialog" style="display: none;">
    <form class="form-horizontal">
        <input type="text" id="fatherId"/>
        <div class="form-group">
            <label for="deptName" class="col-md-2 control-label">部门名</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="deptName" placeholder="部门名...">
            </div>
        </div>
        <div class="form-group">
            <label for="productName" class="col-md-2 control-label">部门描述</label>
            <div class="col-md-4">
                <textarea class="form-control" id="remark"></textarea>
            </div>
        </div>

    </form>
</div>
<div id="editDeptDialog" style="display: none;">
    <form class="form-horizontal">
        <input type="text" id="edit_id"/>
        <div class="form-group">
            <label for="deptName" class="col-md-2 control-label">部门名</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="edit_deptName" placeholder="部门名...">
            </div>
        </div>
        <div class="form-group">
            <label for="productName" class="col-md-2 control-label">部门描述</label>
            <div class="col-md-4">
                <textarea class="form-control" id="edit_remark"></textarea>
            </div>
        </div>

    </form>
</div>
<jsp:include page="/WEB-INF/common/script.jsp"/>
<script>
    $(function () {
        // 初始化产品表
        initProductTable();
        // 初始化所有的绑定事件
        initBindEvent();
        // 初始化日期控件
        initDate();
        // 初始化品牌列表
        initBrandList("brandSelect", '${product.brand.id}');
        // 初始化部门树
        initDeptTree();
    })


    function deleteDept() {

        // 获取当前选中的节点集合
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        var nodes = treeObj.getSelectedNodes();
        if (nodes.length > 0) {

            bootbox.confirm({
                message: "你确定删除吗?",
                size: 'small',
                title: "提示信息",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确定',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {
                    if (result) {
                        var nodeArr = treeObj.transformToArray(nodes);
                        var idArr = [];
                        for (var i = 0; i < nodeArr.length; i++) {
                            idArr.push(nodeArr[i].id);
                        }
                        $.ajax({
                            type:"post",
                            url:"<%=request.getContextPath()%>/dept/deleteDept.jhtml",
                            data:{"ids":idArr},
                            success:function(result){
                                // 刷新前台
                                if (result.code == 200) {
                                    for (var i = 0; i < nodeArr.length; i++) {
                                        treeObj.removeNode(nodeArr[i]);
                                    }
                                }
                            }
                        })
                    }
                }
            })

        } else {
            bootbox.alert({
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>请选择要删除的部门",
                size: 'small',
                title: "提示信息"
            });
        }
    }

    function showEditDialog() {
        // 获取当前选中的节点集合
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        var nodes = treeObj.getSelectedNodes();
        if (nodes.length == 1) {
            var v_selectNode = nodes[0];
            $("#edit_id").attr("value", v_selectNode.id);
            $("#edit_deptName").attr("value", v_selectNode.deptName);
            $("#edit_remark").html(v_selectNode.remark);

            console.log(v_selectNode.id);
            var v_editDeptDialog = bootbox.dialog({
                title: '修改部门',
                message: $("#editDeptDialog").html(),
                size:"large",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确认',
                        className: 'btn-primary',
                        callback: function(){
                            var v_edit_deptName = $("#edit_deptName",  v_editDeptDialog).val();
                            var v_edit_id = $("#edit_id",  v_editDeptDialog).val();
                            var v_edit_remark = $("#edit_remark",  v_editDeptDialog).val();
                            var v_param = {};
                            v_param.deptName = v_edit_deptName;
                            v_param.id = v_edit_id;
                            v_param.remark = v_edit_remark;
                            $.ajax({
                                type:"post",
                                url:"<%=request.getContextPath()%>/dept/updateDept.jhtml",
                                data:v_param,
                                success:function (result) {
                                    if (result.code == 200) {
                                        // 成功,动态ztree节点
                                        // var treeObj = $.fn.zTree.getZTreeObj("deptTree");
                                        // var node = {};
                                        // node.deptName = v_deptName;
                                        // node.remark = v_remark;
                                        // node.id = result.data;
                                        // treeObj.addNodes(v_selectNode, node);
                                    } else {
                                        bootbox.alert({
                                            message: "<span class='glyphicon glyphicon-exclamation-sign'></span>修改部门失败",
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                    }
                                }
                            })
                        }
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                }
            });
        } else {
            bootbox.alert({
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>请选择一个部门",
                size: 'small',
                title: "提示信息"
            });
        }
    }

    function showAddDialog() {
        // 获取当前选中的节点集合
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        var nodes = treeObj.getSelectedNodes();
        if (nodes.length == 1) {
            var v_selectNode = nodes[0];
            $("#fatherId").attr("value", v_selectNode.id);
            console.log(v_selectNode.id);
            var v_addDeptDialog = bootbox.dialog({
                title: '添加部门',
                message: $("#addDeptDialog").html(),
                size:"large",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确认',
                        className: 'btn-primary',
                        callback: function(){
                            var v_deptName = $("#deptName",  v_addDeptDialog).val();
                            var v_fatherId = $("#fatherId",  v_addDeptDialog).val();
                            var v_remark = $("#remark",  v_addDeptDialog).val();
                            var v_param = {};
                            v_param.deptName = v_deptName;
                            v_param.fatherId = v_fatherId;
                            v_param.remark = v_remark;
                            $.ajax({
                                type:"post",
                                url:"<%=request.getContextPath()%>/dept/addDept.jhtml",
                                data:v_param,
                                success:function (result) {
                                    if (result.code == 200) {
                                        // 成功,动态ztree节点
                                        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
                                        var node = {};
                                        node.deptName = v_deptName;
                                        node.remark = v_remark;
                                        node.id = result.data;
                                        treeObj.addNodes(v_selectNode, node);
                                    } else {
                                        bootbox.alert({
                                            message: "<span class='glyphicon glyphicon-exclamation-sign'></span>添加部门失败",
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                    }
                                }
                            })
                        }
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                }
            });
        } else {
            bootbox.alert({
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>请选择一个部门",
                size: 'small',
                title: "提示信息"
            });
        }

    }
    
    function initDeptTree() {
        // 进行ztree的属性配置
        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    pIdKey: "fatherId"
                },
                key: {
                    name: "deptName"
                }
            }
        };

        // ztree展示的数据
        // 发送ajax请求
        $.ajax({
            type:"post",
            url:"<%=request.getContextPath()%>/dept/findList.jhtml",
            success:function (result) {
                if (result.code == 200) {
                    // 渲染ztree
                    $.fn.zTree.init($("#deptTree"), setting, result.data);
                } else {
                    bootbox.alert({
                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>获取部门数据失败",
                        size: 'small',
                        title: "提示信息"
                    });
                }

            }
        })

    }
    
    function initDate() {
        $('#minInsertTime').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: 'zh-CN',
            showClear: true
        });
        $('#maxInsertTime').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: 'zh-CN',
            showClear: true
        });
    }

    var v_ids = [];

    function initBindEvent() {
        $("#productTable tbody").on("click", "tr", function () {
            // 如果复选框处于选中状态，则清除复选框的选中状态，清除背景色，清除数组中对应的id
            var v_checkbox = $(this).find("input[type='checkbox']")[0];
            if (v_checkbox.checked) {
                v_checkbox.checked = false;
                $(this).css("background", "");
                delIds(v_checkbox.value);
                console.log(v_ids);
            } else {
                // 如果复选框未选中，则选中复选框，加上背景色，添加id到数组中
                v_checkbox.checked = true;
                $(this).css("background", "red");
                v_ids.push(v_checkbox.value);
                console.log(v_ids);
            }
        })
    }

    function delIds(id) {
        for (var i = v_ids.length - 1; i >= 0; i--) {
            if (v_ids[i] == id) {
                v_ids.splice(i, 1);
                break;
            }
        }
    }

    function isExist(id) {
        for (var i = 0; i < v_ids.length; i++) {
            if (v_ids[i] == id) {
                return true;
            }
        }
    }


    function search() {
        var v_productName = $("#productName").val();
        var v_minPrice = $("#minPrice").val();
        var v_maxPrice = $("#maxPrice").val();
        var v_minInsertTime = $("#minInsertTime").val();
        var v_maxInsertTime = $("#maxInsertTime").val();
        var v_brandId = $("#brandSelect").val();


        var param = {};
        param.productName = v_productName;
        param.minPrice = v_minPrice;
        param.maxPrice = v_maxPrice;
        param.minInsertTime = v_minInsertTime;
        param.maxInsertTime = v_maxInsertTime;
        param["brand.id"] = v_brandId;

        productTable.settings()[0].ajax.data = param;
        productTable.ajax.reload();
    }

    var productTable;

    function initProductTable() {
        productTable = $('#productTable').DataTable({
            // 是否允许检索
            "searching": false,
            "order": [],
            "lengthMenu": [15, 30, 45],
            "processing": true,
            "serverSide": true,
            "language": {
                "url": "/js/datatable/Chinese.json"
            },
            "ajax": {
                "url": "<%=request.getContextPath()%>/product/list.jhtml",
                "type": "POST",
                "dataSrc": function (result) {
                    if (result.code == 200) {
                        result.draw = result.data.draw;
                        result.recordsTotal = result.data.recordsTotal;
                        result.recordsFiltered = result.data.recordsFiltered;
                        return result.data.data;
                    }
                }
            },
            "drawCallback": function () {
                // 获取当前表格所有行中复选框的值
                $("#productTable tbody tr input[type='checkbox']").each(function () {
                    var v_id = $(this).val();
                    // 当前值和数组中的值进行对比，如果一致，则当前行回填
                    if (isExist(v_id)) {
                        $(this).closest("tr").css("background", "red");
                        this.checked = true;
                    }
                })
            },
            "columns": [
                {
                    "data": "id",
                    "orderable": false,
                    render: function (d, x, r, z) {
                        return "<input type='checkbox' value='" + d + "'/>";
                    }
                },

                {
                    "data": "productName",
                    "orderable": false
                },
                {
                    "data": "productImagePath",
                    "orderable": false,
                    "render": function (data, type, row, meta) {
                        return "<img src='" + data + "' width='100px' height='100px'>";
                    }
                },
                {"data": "productPrice"},
                {
                    "data": "brandName",
                    "orderable": false
                },
                {
                    "data": "insertTimeStr"
                },
                {
                    "data": "updateTimeStr"
                },
                {
                    "data": "id",
                    "orderable": false,
                    "render": function (data, type, row, meta) {
                        return '<div class="btn-group" role="group" aria-label="...">\n' +
                            '                                    <button type="button" class="btn btn-danger" onclick="deleteProduct(\'' + data + '\')"><span\n' +
                            '                                            class="glyphicon glyphicon-remove"></span>删除\n' +
                            '                                    </button>\n' +
                            '                                    <button type="button" class="btn btn-info" onclick="toUpdate(\'' + data + '\')"><span\n' +
                            '                                            class="glyphicon glyphicon-pencil"></span>修改\n' +
                            '                                    </button>\n' +
                            '                                </div>';
                    }
                }
            ]
        });
    }


    function toUpdate(id) {
        location.href = "/product/findproduct.jhtml?id=" + id;
    }

    function deleteProduct(id) {
        bootbox.confirm({
            message: "你确定删除吗?",
            size: 'small',
            title: "提示信息",
            buttons: {
                confirm: {
                    label: '<span class="glyphicon glyphicon-ok"></span>确定',
                    className: 'btn-success'
                },
                cancel: {
                    label: '<span class="glyphicon glyphicon-remove"></span>取消',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajax({
                        type: "post",
                        url: "<%=request.getContextPath()%>/product/deleteproduct.jhtml",
                        data: {"id": id},
                        success: function (result) {
                            if (result.code == 200) {
                                // 刷新
                                search();
                            } else {
                                bootbox.alert({
                                    message: "<span class='glyphicon glyphicon-exclamation-sign'></span>删除失败",
                                    size: 'small',
                                    title: "提示信息"
                                });
                            }
                        }


                    })
                }
            }
        })
    }
</script>

</body>
</html>
