<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/6 0006
  Time: 下午 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>用户管理</title>
    <jsp:include page="/WEB-INF/common/head.jsp"/>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    部门管理
                    <button type="button" class="btn btn-success" onclick="showAddDialog()"><span
                            class="glyphicon glyphicon-plus"></span>新增
                    </button>
                    <button type="button" class="btn btn-warning" onclick="showEditDialog()"><span
                            class="glyphicon glyphicon-pencil"></span>修改
                    </button>
                    <button type="button" class="btn btn-danger" onclick="deleteDept();"><span
                            class="glyphicon glyphicon-trash"></span>删除
                    </button>

                </div>
                <div class="panel-body">
                    <ul id="deptTree" class="ztree"></ul>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-primary">
                <div class="panel-heading">用户查询</div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-2 control-label">用户名</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="query_userName" placeholder="用户名...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">薪资</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="query_minSalary" placeholder="最低工资">
                                    <span class="input-group-addon">
                    <i class="glyphicon glyphicon-yen"></i>
                </span>
                                    <input type="text" class="form-control" id="query_maxSalary" placeholder="最高工资">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">生日</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="query_minBirthday" placeholder="开始时间">
                                    <span class="input-group-addon">
                    <i class="glyphicon glyphicon-time"></i>
                </span>
                                    <input type="text" class="form-control" id="query_maxBirthday" placeholder="结束时间">
                                </div>
                            </div>
                        </div>

                        <div style="text-align: center;">
                            <button type="button" class="btn btn-primary" onclick="search()"><span
                                    class="glyphicon glyphicon-ok"></span>查询
                            </button>
                            <button type="button" class="btn btn-default" onclick="clearInfo();"><span
                                    class="glyphicon glyphicon-refresh" ></span>重置
                            </button>
                        </div>

                        <input type="text" id="deptIds"/>

                    </form>
                </div>


            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    用户列表
                    <button type="button" class="btn btn-primary" onclick="showAddUserDialog();"><span
                            class="glyphicon glyphicon-plus"></span>增加
                    </button>
                    <button type="button" class="btn btn-primary" onclick="showEditUserDialog();"><span
                            class="glyphicon glyphicon-plus"></span>修改
                    </button>
                    <button type="button" class="btn btn-danger" onclick="showDeptDialog();"><span
                            class="glyphicon glyphicon-plus"></span>批量换部门
                    </button>
                </div>
                <table id="userTable" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>用户名</th>
                        <th>头像</th>
                        <th>真实名</th>
                        <th>性别</th>
                        <th>生日</th>
                        <th>部门</th>
                        <th>薪资</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>选择</th>
                        <th>用户名</th>
                        <th>头像</th>
                        <th>真实名</th>
                        <th>性别</th>
                        <th>生日</th>
                        <th>部门</th>
                        <th>薪资</th>
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
            <label  class="col-md-2 control-label">部门描述</label>
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
            <label  class="col-md-2 control-label">部门描述</label>
            <div class="col-md-4">
                <textarea class="form-control" id="edit_remark"></textarea>
            </div>
        </div>

    </form>
</div>

<div id="addUserDialog" style="display: none;">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="userName" class="col-md-2 control-label">用户名</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="userName" placeholder="用户名...">
            </div>
        </div>
        <div class="form-group">
            <label for="userName" class="col-md-2 control-label">头像</label>
            <div class="col-md-6">
                <input type="file" class="form-control" name="uploadFileInfo" id="add_headerImage">
                <input type="text" class="form-control"  id="headerPath">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">密码</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="password" placeholder="请输入密码...">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">确认密码</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="confirmPassword" placeholder="再次确认密码...">
            </div>
        </div>
        <div class="form-group">
            <label for="realName" class="col-md-2 control-label">真实姓名</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="realName" placeholder="真实姓名...">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">性别</label>
            <div class="col-md-4">
                <input type="radio" name="sex" value="1">男
                <input type="radio" name="sex" value="0">女
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">生日</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="birthday" placeholder="生日">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">薪资</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="salary" placeholder="薪资">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">部门</label>
            <div class="col-md-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="deptNameInfo" placeholder="部门">
                    <input type="hidden" class="form-control" id="deptId">
                    <span class="input-group-btn">
                        <button class="btn btn-primary" type="button" onclick="showDeptTreeDialog(v_addUserDialog, 'deptNameInfo', 'deptId');">选择部门...</button>
                    </span>
                </div><!-- /input-group -->
            </div>
        </div>

    </form>
</div>


<div id="editUserDialog" style="display: none;">

    <form class="form-horizontal">
        <input type="text" id="userId"/>
        <div class="form-group">
            <label for="userName" class="col-md-2 control-label">用户名</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="edit_userName" placeholder="用户名...">
            </div>
        </div>
        <div class="form-group">
            <label for="userName" class="col-md-2 control-label">头像</label>
            <div class="col-md-6">
                <input type="file" class="form-control" name="uploadFileInfo" id="edit_headerImage">
                <input type="text" class="form-control"  id="old_headerPath">
                <input type="text" class="form-control"  id="edit_headerPath">
            </div>
        </div>
        <div class="form-group">
            <label for="realName" class="col-md-2 control-label">真实姓名</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="edit_realName" placeholder="真实姓名...">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">性别</label>
            <div class="col-md-4">
                <input type="radio" name="edit_sex" value="1">男
                <input type="radio" name="edit_sex" value="0">女
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">生日</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="edit_birthday" placeholder="生日">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">薪资</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="edit_salary" placeholder="薪资">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">部门</label>
            <div class="col-md-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="edit_deptNameInfo" placeholder="部门">
                    <input type="text" class="form-control" id="edit_deptId">
                    <span class="input-group-btn">
                        <button class="btn btn-primary" type="button" onclick="showDeptTreeDialog(v_editUserDialog, 'edit_deptNameInfo', 'edit_deptId');">选择部门...</button>
                    </span>
                </div><!-- /input-group -->
            </div>
        </div>

    </form>
</div>

<div id="treeDialog" style="display: none;">
    <ul id="deptInfoTree" class="ztree"></ul>
</div>

<jsp:include page="/WEB-INF/common/script.jsp"/>
<script>

    var addUserDialogSource;
    $(function () {

        addUserDialogSource = $("#addUserDialog").html();
        // 初始化部门树
        initDeptTree();
        // 初始化员工表
        initUserTable();

        initBindEvent();

        initDate();

        initEditDate();

        initSearchDate();

        initHeaderImage();

    })


    function initHeaderImage() {
        var s = {
            language: 'zh',
            uploadUrl: "/file/upload.jhtml",
            showUpload : false,
            showRemove : false,
            allowedPreviewTypes : [ 'image'],  //预览的文件类型
            allowedFileExtensions : [ 'jpg', 'png', 'gif'] //上传的文件的后缀名
        };

        $("#add_headerImage").fileinput(s).on("fileuploaded", function(event, t, previewId, index) {
            console.log(t);
            console.log(previewId);
            console.log(index);
            var result = t.response;
            if (result.code == 200) {
               $("#headerPath", v_addUserDialog).val(result.data);
            }
        });

    }
    
    function initEditUserHeader(headerImageArr) {
        var s = {
            language: 'zh',
            uploadUrl: "/file/upload.jhtml",
            showUpload : false,
            showRemove : false,
            initialPreview:headerImageArr,
            initialPreviewAsData: true,
            allowedPreviewTypes : [ 'image'],  //预览的文件类型
            allowedFileExtensions : [ 'jpg', 'png', 'gif'] //上传的文件的后缀名
        };

        $("#edit_headerImage").fileinput(s).on("fileuploaded", function(event, t, previewId, index) {
            console.log(t);
            console.log(previewId);
            console.log(index);
            var result = t.response;
            if (result.code == 200) {
                $("#edit_headerPath", v_editUserDialog).val(result.data);
            }
        });
    }

    var v_editUserDialog;
    function showEditUserDialog() {
        var v_source = $("#editUserDialog").html();
        // 获取选中的行的id
        var v_id = "";
        var v_selectedRows_count = $("#userTable tbody tr input[type='checkbox']:checked").length;
        if (v_selectedRows_count == 1) {
            v_id = $("#userTable tbody tr input[type='checkbox']:checked")[0].value;
            // 发送ajax请求获取员工数据
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/user/findUser.jhtml",
                data:{"id":v_id},
                success:function (result) {
                    if (result.code == 200) {
                        var userData = result.data;
                        $("#edit_userName").val(userData.userName);
                        $("#edit_realName").val(userData.realName);
                        $("input[name='edit_sex'][value='"+userData.sex+"']").prop("checked", true);
                        $("#edit_birthday").val(userData.birthday);
                        $("#edit_salary").val(userData.salary);
                        $("#edit_deptNameInfo").val(userData.deptName);
                        $("#edit_deptId").val(userData.deptId);
                        $("#userId").val(userData.id);
                        // 初始化头像
                        var headerImageArr = [];
                        headerImageArr.push(userData.headerPath);
                        initEditUserHeader(headerImageArr);
                        // 回填老路径
                        $("#old_headerPath").val(userData.headerPath);
                        // 弹出对话框
                        v_editUserDialog = bootbox.dialog({
                            title: '修改员工',
                            message: $("#editUserDialog form"),
                            size: "large",
                            buttons: {
                                confirm: {
                                    label: '<span class="glyphicon glyphicon-ok"></span>确认',
                                    className: 'btn-primary',
                                    callback: function () {
                                        var v_param = {};
                                        var v_edit_userName = $("#edit_userName", v_editUserDialog).val();
                                        var v_edit_realName = $("#edit_realName", v_editUserDialog).val();
                                        var v_edit_sex = $("input[name='edit_sex']:checked", v_editUserDialog).val();
                                        var v_edit_birthday = $("#edit_birthday", v_editUserDialog).val();
                                        var v_edit_salary = $("#edit_salary", v_editUserDialog).val();
                                        var v_edit_deptId = $("#edit_deptId", v_editUserDialog).val();
                                        var v_userId = $("#userId", v_editUserDialog).val();
                                        var v_oldHeaderPath = $("#old_headerPath", v_editUserDialog).val();
                                        var v_editHeaderPath = $("#edit_headerPath", v_editUserDialog).val();
                                        v_param.userName = v_edit_userName;
                                        v_param.realName = v_edit_realName;
                                        v_param.sex = v_edit_sex;
                                        v_param.birthday = v_edit_birthday;
                                        v_param.salary = v_edit_salary;
                                        v_param.deptId = v_edit_deptId;
                                        v_param.id = v_userId;
                                        v_param.oldHeaderPath = v_oldHeaderPath;
                                        v_param.headerPath = v_editHeaderPath;
                                        $.ajax({
                                            type:"post",
                                            url:"<%=request.getContextPath()%>/user/updateUser.jhtml",
                                            data:v_param,
                                            success:function (result) {
                                                if (result.code == 200) {
                                                    // 刷新
                                                    search();
                                                } else {
                                                    bootbox.alert({
                                                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>修改失败",
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
                                    className: 'btn-danger',

                                }
                            }
                        });

                        $("#editUserDialog").html(v_source);
                        initEditDate();
                    }
                }
            })
        } else {
            bootbox.alert({
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>只能选择一个员工",
                size: 'small',
                title: "提示信息"
            });
        }

    }
    
    function clearInfo() {
        console.log("============");
        // 清空查询信息
        $("#query_userName").val("");
        $("#query_minSalary").val("");
        $("#query_maxSalary").val("");
        $("#query_minBirthday").val("");
        $("#query_maxBirthday").val("");
        $("#deptIds").val("");
        // 清空数组
        v_idArr = [];
        // 清空选中节点
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        treeObj.cancelSelectedNode();
        // 刷新
        search();
    }

    function search() {
        var param = {};
        var v_userName = $("#query_userName").val();
        var v_minSalary = $("#query_minSalary").val();
        var v_maxSalary = $("#query_maxSalary").val();
        var v_minBirthday = $("#query_minBirthday").val();
        var v_maxBirthday = $("#query_maxBirthday").val();
        var v_deptIds = $("#deptIds").val();

        param.userName = v_userName;
        param.minSalary = v_minSalary;
        param.maxSalary = v_maxSalary;
        param.minBirthday = v_minBirthday;
        param.maxBirthday = v_maxBirthday;
        param.deptIds = v_deptIds;

        userTable.settings()[0].ajax.data = param;
        userTable.ajax.reload();
    }

    function showDeptTreeDialog(dialog, deptNameId, deptId) {
        // 获取原有数据
        var v_source = $("#treeDialog").html();
        // 发送ajax请求获取数据
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/dept/findList.jhtml",
            success: function (result) {
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
                if (result.code == 200) {
                    // 渲染ztree
                    $.fn.zTree.init($("#deptInfoTree"), setting, result.data);
                    // 弹出对话框
                    bootbox.dialog({
                        title: '选择部门',
                        message: $("#deptInfoTree"),
                        size: "large",
                        buttons: {
                            confirm: {
                                label: '<span class="glyphicon glyphicon-ok"></span>确认',
                                className: 'btn-primary',
                                callback: function () {
                                    // 获取树的选中节点
                                    var treeObj = $.fn.zTree.getZTreeObj("deptInfoTree");
                                    var nodes = treeObj.getSelectedNodes();
                                    if (nodes.length == 1) {
                                        var v_deptId = nodes[0].id;
                                        var v_deptName = nodes[0].deptName;
                                        // 回填到用户对话框中的指定元素中
                                        $("#"+deptNameId, dialog).val(v_deptName);
                                        $("#"+deptId, dialog).val(v_deptId);
                                    } else {
                                        bootbox.alert({
                                            message: "<span class='glyphicon glyphicon-exclamation-sign'></span>只能选择一个部门",
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                    }
                                }
                            },
                            cancel: {
                                label: '<span class="glyphicon glyphicon-remove"></span>取消',
                                className: 'btn-danger',

                            }
                        }
                    });
                    // 回填
                    $("#treeDialog").html(v_source);
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


    function showDeptDialog(dialog, deptNameId, deptId) {
        if (v_ids.length == 0)
        {
            bootbox.alert({
                message: "<span class='glyphicon glyphicon-exclamation-sign'></span>请选择员工",
                size: 'small',
                title: "提示信息"
            });
            return;
        }
        // 获取原有数据
        var v_source = $("#treeDialog").html();
        // 发送ajax请求获取数据
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/dept/findList.jhtml",
            success: function (result) {
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
                if (result.code == 200) {
                    // 渲染ztree
                    $.fn.zTree.init($("#deptInfoTree"), setting, result.data);
                    // 弹出对话框
                    bootbox.dialog({
                        title: '选择部门',
                        message: $("#deptInfoTree"),
                        size: "large",
                        buttons: {
                            confirm: {
                                label: '<span class="glyphicon glyphicon-ok"></span>确认',
                                className: 'btn-primary',
                                callback: function () {
                                    // 获取树的选中节点
                                    var treeObj = $.fn.zTree.getZTreeObj("deptInfoTree");
                                    var nodes = treeObj.getSelectedNodes();
                                    if (nodes.length == 1) {
                                        var v_deptId = nodes[0].id;
                                        $.ajax({
                                            type:"post",
                                            url:"<%=request.getContextPath()%>/user/batchChangeDept.jhtml",
                                            data:{"ids":v_ids, "deptId":v_deptId},
                                            success:function (result) {
                                                if (result.code == 200) {
                                                    search();
                                                }
                                            }
                                        })

                                    } else {
                                        bootbox.alert({
                                            message: "<span class='glyphicon glyphicon-exclamation-sign'></span>只能选择一个部门",
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                    }
                                }
                            },
                            cancel: {
                                label: '<span class="glyphicon glyphicon-remove"></span>取消',
                                className: 'btn-danger',

                            }
                        }
                    });
                    // 回填
                    $("#treeDialog").html(v_source);
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

    function initSearchDate() {
        $('#query_minBirthday').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: 'zh-CN',
            showClear: true
        });

        $('#query_maxBirthday').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: 'zh-CN',
            showClear: true
        });
    }

    function initEditDate() {
        $('#edit_birthday').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: 'zh-CN',
            showClear: true
        });
    }

    function initDate() {
        $('#birthday').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: 'zh-CN',
            showClear: true
        });
    }
    var userTable;
    function initUserTable() {
        userTable = $('#userTable').DataTable({
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
                "url": "<%=request.getContextPath()%>/user/list.jhtml",
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
                $("#userTable tbody tr input[type='checkbox']").each(function () {
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
                    "data": "userName",
                    "orderable": false
                },
                {
                    "data": "headerPath",
                    "orderable": false,
                    render: function (d, x, r, z) {
                        return "<img src='"+d+"' width='100px' height='100px'/>";
                    }
                },
                {
                    "data": "realName",
                    "orderable": false
                },
                {
                    "data": "sex",
                    "orderable": false,
                    render: function (d, x, r, z) {
                        if (d == '1') {
                            return "男";
                        } else {
                            return "女";
                        }
                    }
                },
                {
                    "data": "birthday"
                },
                {
                    "data": "deptName",
                    "orderable": false
                },
                {
                    "data": "salary"
                },
                {
                    "data": "id",
                    "orderable": false,
                    "render": function (data, type, row, meta) {
                        return '<div class="btn-group" role="group" aria-label="...">\n' +
                            '                                    <button type="button" class="btn btn-danger" onclick="deleteUser(\'' + data + '\')"><span\n' +
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


    function deleteUser(uid) {
        $.ajax({
            type:"post",
            url:"/user/deleteUser.jhtml",
            data:{"id":uid},
            success:function(result) {
                if (result.code == 200) {
                    // 刷新
                    search();
                }
            }
        })
    }

    var v_ids = [];

    function initBindEvent() {
        $("#userTable tbody").on("click", "tr", function () {
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

    function initDeptTree() {
        // 进行ztree的属性配置
        var setting = {
            callback: {
                onClick: zTreeOnClick
            },
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
            type: "post",
            url: "<%=request.getContextPath()%>/dept/findList.jhtml",
            success: function (result) {
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
    var v_idArr = [];
    function zTreeOnClick() {
        // 获取当前选中的节点集合
        v_idArr = [];
        $("#deptIds").val("");
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        var nodes = treeObj.getSelectedNodes();
        var nodeArr = treeObj.transformToArray(nodes);
        for (var i = 0; i < nodeArr.length; i++) {
           // $("#deptIds").val( $("#deptIds").val()+","+nodeArr[i].id);
            if (!isExist(nodeArr[i].id)) {
                v_idArr.push(nodeArr[i].id);
            }
        }
        $("#deptIds").val(v_idArr.join(","));
        search();
    }

    function isExist(id) {
       for (var i = 0; i < v_idArr.length; i++) {
           if (v_idArr[i] == id) {
               return true;
           }
       }
    }

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
                            type: "post",
                            url: "<%=request.getContextPath()%>/dept/deleteDept.jhtml",
                            data: {"ids": idArr},
                            success: function (result) {
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
                size: "large",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确认',
                        className: 'btn-primary',
                        callback: function () {
                            var v_edit_deptName = $("#edit_deptName", v_editDeptDialog).val();
                            var v_edit_id = $("#edit_id", v_editDeptDialog).val();
                            var v_edit_remark = $("#edit_remark", v_editDeptDialog).val();
                            var v_param = {};
                            v_param.deptName = v_edit_deptName;
                            v_param.id = v_edit_id;
                            v_param.remark = v_edit_remark;
                            $.ajax({
                                type: "post",
                                url: "<%=request.getContextPath()%>/dept/updateDept.jhtml",
                                data: v_param,
                                success: function (result) {
                                    if (result.code == 200) {
                                        // 成功,动态ztree节点
                                        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
                                        v_selectNode.deptName = v_edit_deptName;
                                        v_selectNode.remark = v_edit_remark;
                                        treeObj.updateNode(v_selectNode);
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

    var v_addUserDialog;

    function showAddUserDialog() {
        // 获取原始内容

        // 获取当前选中的节点集合
        v_addUserDialog = bootbox.dialog({
            title: '添加用户',
            message: $("#addUserDialog form"),
            size: "large",
            buttons: {
                confirm: {
                    label: '<span class="glyphicon glyphicon-ok"></span>确认',
                    className: 'btn-primary',
                    callback: function () {
                        var v_userName = $("#userName", v_addUserDialog).val();
                        var v_password = $("#password", v_addUserDialog).val();
                        var v_realName = $("#realName", v_addUserDialog).val();
                        var v_sex = $("input[name='sex']:checked", v_addUserDialog).val();
                        var v_birthday = $("#birthday", v_addUserDialog).val();
                        var v_salary = $("#salary", v_addUserDialog).val();
                        var v_deptId = $("#deptId", v_addUserDialog).val();
                        var v_headerPath = $("#headerPath", v_addUserDialog).val();
                        var v_param = {};
                        v_param.userName = v_userName;
                        v_param.userPwd = v_password;
                        v_param.realName = v_realName;
                        v_param.sex = v_sex;
                        v_param.birthday = v_birthday;
                        v_param.salary = v_salary;
                        v_param.deptId = v_deptId;
                        v_param.headerPath = v_headerPath;
                        $.ajax({
                            type: "post",
                            url: "<%=request.getContextPath()%>/user/addUser.jhtml",
                            data: v_param,
                            success: function (result) {
                                if (result.code == 200) {
                                    // 成功,动态ztree节点
                                    search();
                                } else {
                                    bootbox.alert({
                                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>添加用户失败",
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
                    className: 'btn-danger',

                }
            }
        });

        // 回填内容并再次进行组件的初始化
        $("#addUserDialog").html(addUserDialogSource);
        initDate();
        initHeaderImage();

    }
</script>
</body>
</html>
