<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 产品信息</title>
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
<div class="container-fluid larry-wrapper">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <section class="panel panel-padding">
                <form id="form1" class="layui-form" lay-filter="form">

                    <div class="layui-form-item">
                        <input type="hidden" name="id" value="${id}" >
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">作品名称</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${productName}" class="layui-input" disabled="">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-input-inline">
                            <input type="text" name="buyerEmail" lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">学校</label>
                        <div class="layui-input-inline">
                            <input type="text" name="buyerSchool" lay-verify="required" placeholder="请输入学校" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn" lay-submit lay-filter="sendTo">立即发送</button>
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
    }).use('admin/product/send');
</script>
</body>

</html>
