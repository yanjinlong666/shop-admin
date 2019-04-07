<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/js/bootstrap3/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            padding-top: 50px;
        }

        .show-grid [class ^="col-"] {
            padding-top: 10px;
            padding-bottom: 10px;
            background-color: #eee;
            border: 1px solid #ddd;
            background-color: rgba(86, 61, 124, .15);
            border: 1px solid rgba(86, 61, 124, .2);
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-rexample-navbar-collapse-1" aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">飞狐教育电商后台</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-rexample-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#">校园风采 <span class="sr-only">(current)</span></a></li>
                <li class="active"><a href="#">师资团队</a></li>
                <li><a href="#">明星学员</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">java课程 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">云计算</a></li>
                        <li><a href="#">云应用</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">云服务</a></li>
                        <li><a href="#">大数据</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">数据挖掘</a></li>
                    </ul>
                </li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4" style="padding-left: 0px;">
            <div class="list-group">
                <a href="#" class="list-group-item active">
                    Cras justo odio
                </a>
                <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
                <a href="#" class="list-group-item">Morbi leo risus</a>
                <a href="#" class="list-group-item">Porta ac consectetur ac</a>
                <a href="#" class="list-group-item">Vestibulum at eros</a>
            </div>
        </div>
        <div class="col-md-8">
            <form class="form-horizontal">
                <fieldset>
                    <legend>配置数据源</legend>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-md-2 control-label">Email</label>
                        <div class="col-md-4">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                        </div>
                        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-4">
                            <div class="input-group">
                                <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
                                <span class="input-group-addon" id="sizing-addon1">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                            </span>
                                <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
                            </div><!-- /input-group -->

                        </div>
                        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                        </div>
                    </div>
                    <div style="text-align: center;">
                        <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span>提交
                        </button>
                        <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-refresh"></span>重置
                        </button>
                    </div>
                </fieldset>
            </form>
            <div style="background: #1b6d85;width: 100%;text-align: right;">
                <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span>提交
                </button>
                <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-refresh"></span>重置
                </button>
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">用户列表</h3>
                </div>
                <div class="table-responsive ">
                    <table class="table table-bordered">
                        <tr>
                            <th>选择</th>
                            <th>用户名</th>
                            <th>密码</th>
                            <th>年龄</th>
                            <th>性别</th>
                            <th>操作</th>
                        </tr>
                        <tr>
                            <td>选择11</td>
                            <td>用户名11</td>
                            <td>密码12</td>
                            <td>年龄12</td>
                            <td>性别12</td>
                            <td>
                                <div class="btn-group" role="group" aria-label="...">
                                    <button type="button" class="btn btn-danger btn-xs"><span
                                            class="glyphicon glyphicon-remove"></span>删除
                                    </button>
                                    <button type="button" class="btn btn-info btn-xs"><span
                                            class="glyphicon glyphicon-pencil"></span>修改
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>选择</td>
                            <td>用户名</td>
                            <td>密码</td>
                            <td>年龄</td>
                            <td>性别</td>
                            <td>
                                <div class="btn-group" role="group" aria-label="...">
                                    <button type="button" class="btn btn-danger btn-xs"><span
                                            class="glyphicon glyphicon-remove"></span>删除
                                    </button>
                                    <button type="button" class="btn btn-info btn-xs"><span
                                            class="glyphicon glyphicon-pencil"></span>修改
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>选择</td>
                            <td>用户名</td>
                            <td>密码</td>
                            <td>年龄</td>
                            <td>性别</td>
                            <td>
                                <div class="btn-group" role="group" aria-label="...">
                                    <button type="button" class="btn btn-danger btn-xs"><span
                                            class="glyphicon glyphicon-remove"></span>删除
                                    </button>
                                    <button type="button" class="btn btn-info btn-xs"><span
                                            class="glyphicon glyphicon-pencil"></span>修改
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>选择</td>
                            <td>用户名</td>
                            <td>密码</td>
                            <td>年龄</td>
                            <td>性别</td>
                            <td>
                                <div class="btn-group" role="group" aria-label="...">
                                    <button type="button" class="btn btn-danger btn-xs"><span
                                            class="glyphicon glyphicon-remove"></span>删除
                                    </button>
                                    <button type="button" class="btn btn-info btn-xs"><span
                                            class="glyphicon glyphicon-pencil"></span>修改
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>选择</td>
                            <td>用户名</td>
                            <td>密码</td>
                            <td>年龄</td>
                            <td>性别</td>
                            <td>
                                <div class="btn-group" role="group" aria-label="...">
                                    <button type="button" class="btn btn-danger btn-xs"><span
                                            class="glyphicon glyphicon-remove"></span>删除
                                    </button>
                                    <button type="button" class="btn btn-info btn-xs"><span
                                            class="glyphicon glyphicon-pencil"></span>修改
                                    </button>
                                </div>
                            </td>
                        </tr>

                    </table>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="<%=request.getContextPath()%>/js/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>