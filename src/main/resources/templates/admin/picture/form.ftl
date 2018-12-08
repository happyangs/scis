<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <style type="text/css">
        .layui-form-item{
            margin: 50px 0 0 200px
        }
    </style>
</head>

<body>
<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <legend style="text-align:center;">添加图片</legend>
</fieldset>
<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <input type="hidden" name="id" value="${picture.id}" >
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">产品ID</label>
        <div class="layui-input-inline">
            <input type="text" name="productId" lay-verify="required" placeholder="请输入产品ID" value="${picture.productId}" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">图片类型</label>
        <div class="layui-input-inline">
            <input type="text" name="pictureType" lay-verify="required" placeholder="请输入图片类型" value="${picture.pictureType}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">图片尺寸</label>
        <div class="layui-input-inline">
            <input type="text" name="pictureSize" lay-verify="required" placeholder="请输入图片类型" value="${picture.pictureSize}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">图片链接</label>
        <div class="layui-input-inline">
            <input type="text" name="picturePath" lay-verify="required" placeholder="请输入图片链接" value="${picture.picturePath}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
            <input type="text" name="remark" lay-verify="required" placeholder="请输入备注" value="${picture.remark}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <button class="layui-btn" lay-submit lay-filter="add">立即提交</button>
    </div>
</form>

<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<script src="${ctx!}/js/common.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/admin/'
    }).use('picture/form');
</script>
</body>

</html>
