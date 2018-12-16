<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8" />
    <title>数据列表页面</title>
    <!-- layui.css -->
    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <style>

        tr td:not(:nth-child(0)),
        tr th:not(:nth-child(0)) {
            text-align: center;
        }

        /*可选*/
        .layui-laypage > * {
            float: left;
        }
        .layui-field-title .layui-field-box{
            padding: 10px 20px 10px 30px;
        }
        .layui-table-cell{
            padding-top: 4px;
            height: 45px;
        }

    </style>
</head>
<body>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field" style="float: left;margin: 0px 0 20px;">
    <div style="padding: 40px 0px 0px 80px;">
        <div class="layui-inline">
            <div class="layui-input-inline" style="width:auto">
                <a id="addType" class="layui-btn layui-btn-normal">添加类别</a>
            </div>
        </div>
    </div>
    <div class="layui-field-box">
        <div id="dataContent" class="">
            <table class="layui-hide" id="type" lay-filter="table"></table>
            <script type="text/html" id="operator">
                <a class="layui-btn" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger " lay-event="del">删除</a>
            </script>
        </div>
    </div>
</fieldset>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <div style="padding: 40px 0px 0px 80px;">
        <div class="layui-inline">
            <div class="layui-input-inline" style="width:auto">
                <a id="addType1" class="layui-btn layui-btn-normal">添加子类型</a>
            </div>
        </div>
    </div>
    <div class="layui-field-box">
        <div id="dataContent" class="">
            <table class="layui-hide" id="type1" lay-filter="table"></table>
            <script type="text/html" id="operator">
                <a class="layui-btn" lay-event="edit1">编辑</a>
                <a class="layui-btn layui-btn-danger " lay-event="del1">删除</a>
            </script>
        </div>
    </div>
</fieldset>

<script type="text/html" id="configType">
    <a href="/admin/config/subType?subType={{d.configType}}" class="layui-table-link" target="_blank">{{ d.configType }}</a>
</script>

<!-- layui.js -->
<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/admin/'
    }).use('config/index');
</script>
</body>
</html>