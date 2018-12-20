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
            margin: 20px 0 0 200px
        }
    </style>
</head>

<body>
<div class="container-fluid larry-wrapper">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <section class="panel panel-padding">
                <form id="form1" class="layui-form "  lay-filter="form">
                    <div class="layui-form-item">
                        <input type="hidden" name="id"  value="${(order.id)!}" >
                    </div>

                    <div class="layui-form-item" style="margin-top: 10px">
                        <label class="layui-form-label">作品编码</label>
                        <div class="layui-input-inline">
                            <input type="text" name="productId" lay-verify="number" disabled="" value="${(order.productId?c)!}"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>
                    <div class="layui-form-item" style="margin-top: 10px">
                        <label class="layui-form-label">作品名称</label>
                        <div class="layui-input-inline">
                            <input type="text" name="productName" lay-verify="require" disabled="" value="${order.productName}"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item" style="margin-top: 10px">
                        <label class="layui-form-label">学校</label>
                        <div class="layui-input-inline">
                            <input type="text" name="buyerSchool" lay-verify="require" value="${order.buyerSchool}"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item" style="margin-top: 10px">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-input-inline">
                            <input type="text" name="buyerEmail" lay-verify="require" value="${order.buyerEmail}"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">是否发送</label>
                        <div class="layui-input-block">
                            <input type="checkbox" name="orderStatus" value="0" title="发送"  <#if (order.orderStatus == 0)> checked=""</#if> />
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
    }).use('admin/order/form');
</script>
</body>

</html>
