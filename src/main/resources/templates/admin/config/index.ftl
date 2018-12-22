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
        .layui-table-cell{
            padding-top: 4px;
            height: 45px;
        }
        .star-so{
            text-align: center;
            margin-bottom: 10px;
            margin-top: 40px;
        }
        .star-so input.layui-input{
            width: 200px;
            display: inline-block;
        }

    </style>
</head>
<body>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <div class="layui-row">
        <div class="layui-form layui-col-md12 star-so">
            <div class="layui-input-inline">
                <select name="configType" id="configType" lay-filter="depts">
                    <option value="">请选择配置项</option>
                <#list configType as x>
                    <option value="${x.configType}">${x.configTypeDesc}</option>
                </#list>
                </select>
            </div>
            <input class="layui-input" placeholder="子类型编码" name="code" id="code">
            <button class="layui-btn" id="search" "><i class="layui-icon">&#xe615;</i></button>
            <button class="layui-btn" style="position: relative;float: right;right: 100px;" onclick="javascript:location.replace(location.href)">
                <i class="layui-icon">&#x1002;</i>
            </button>
        </div>
    </div>


    <div class="layui-inline">
        <div class="layui-input-inline" style="width:auto">
            <a id="addConfig" class="layui-btn layui-btn-normal">添加</a>
        </div>
    </div>



    <div class="layui-field-box">
        <div id="dataContent" class="">

            <table class="layui-hide" id="config" lay-filter="table"></table>
            <script type="text/html" id="operator">
                <a class="layui-btn" lay-event="edit"><i class="layui-icon"></i></a>
                <a class="layui-btn layui-btn-danger" lay-event="del"><i class="layui-icon"></i></a>
            </script>

        </div>
    </div>
</fieldset>

<!-- layui.js -->
<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/'
    }).use('admin/config/index');
</script>
</body>
</html>