<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 学生信息</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <style type="text/css">
        .layui-form-item{
            margin: 20px 0 0 200px
        }

    </style>
</head>

<body>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <legend style="text-align:center;">添加</legend>
</fieldset>
<div class="container-fluid larry-wrapper">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <section class="panel panel-padding">
                <form id="form1" class="layui-form "  lay-filter="form">
                    <div class="layui-form-item">
                        <input type="hidden" name="id"  value="${(config.id)!}" >
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">配置项</label>
                        <div class="layui-input-inline">
                            <select name="configType" lay-filter="depts">
                            <option value="">请选择配置项</option>
                        <#list configType as x>
                            <option value="${x.configType}"<#if (config.configType == x.configType)> selected="selected" </#if>>${x.configTypeDesc}</option>
                        </#list>
                        </select>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">子类型编码</label>
                        <div class="layui-input-inline">
                            <input type="text" name="code" lay-verify="required" placeholder="子类型编码" value="${(config.code?c)!}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">子类型名称</label>
                        <div class="layui-input-inline">
                            <input type="text" name="zhName" lay-verify="required" placeholder="子类型名称" value="${config.zhName}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <button class="layui-btn" lay-submit lay-filter="add">立即提交</button>
                    </div>

                </form>
            </section>
        </div>
    </div>
</div>

<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<script src="${ctx!}/js/common.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/'
    }).use('admin/config/form');
</script>
</body>

</html>
