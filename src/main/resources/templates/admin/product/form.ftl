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
                <form id="form1" class="layui-form" lay-filter="form">
                    <div class="layui-form-item">
                        <input type="hidden" name="id" value="${(product.id?c)!}" >
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">产品类型</label>
                        <div class="layui-input-inline">
                            <select name="productType">
                                <option value="">请选择</option>
                            <#list productType as x>
                                <option value="${x.code}"
                                    <#if (product.productType == x.code)> selected="selected" </#if>
                                >${x.zhName}</option>
                            </#list>
                            </select>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">商品名称</label>
                        <div class="layui-input-inline">
                            <input type="text" name="productName" lay-verify="required" placeholder="请输入产品名称" value="${product.productName}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">首页图片</label>
                        <div class="layui-input-block">
                            <input type="text" name="showPath" lay-verify="required" placeholder="请输入首页图片链接" value="${product.showPath}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">价格</label>
                        <div class="layui-input-inline">
                            <input type="text" name="price" lay-verify="required|number" placeholder="请输入价格" value="${product.price}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">总张数</label>
                        <div class="layui-input-inline">
                            <input type="text" name="htmlNum" lay-verify="required|number" placeholder="请输入总张数" value="${(product.htmlNum?c)!}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">商品描述</label>
                        <div class="layui-input-inline">
                            <input type="text" name="productDesc" lay-verify="required" placeholder="请输入商品描述" value="${product.productDesc}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">云链接</label>
                        <div class="layui-input-block">
                            <input type="text" name="link" lay-verify="required" placeholder="请输入链接" value="${product.link}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">提取码</label>
                        <div class="layui-input-block">
                            <input type="text" name="linkCode" lay-verify="required" placeholder="请输入提取码" value="${product.linkCode}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                   <#-- <div class="layui-form-item">
                        <label class="layui-form-label">详情简介</label>
                        <div class="layui-input-inline">
                            <input type="text" name="productSynopsis" lay-verify="required" placeholder="请输入详情简介" value="${product.productSynopsis}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>-->
                    <div class="layui-form-item">
                        <label class="layui-form-label">是否发布</label>
                        <div class="layui-input-block">
                            <input type="checkbox" name="isDelete" value="0" title="发布"  <#if (product.isDelete == 0)> checked=""</#if> />
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">详情简介</label>
                        <div class="layui-input-block">
                            <textarea name="productSynopsis" lay-verify="required" placeholder="请输入详情简介" class="layui-textarea">${product.productSynopsis}</textarea>
                        </div>
                    </div>

                    <#--<div class="layui-form-item" pane>-->
                        <#--<label class="layui-form-label">开关</label>-->
                        <#--<div class="layui-input-block">-->
                            <#--<input type="checkbox" name="iSwitch" lay-skin="switch" lay-text="ON|OFF">-->
                        <#--</div>-->
                    <#--</div>-->

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
    }).use('admin/product/form');
</script>
</body>

</html>
