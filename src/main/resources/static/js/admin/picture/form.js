layui.define(['element', 'layer', 'form'], function (exports) {
    var form = layui.form;
    var $ = layui.jquery;
    //自定义验证
    form.verify({
        name: function (value) {
            if (value.length <= 0 || value.length > 10) {
                return "账号必须1到10位"
            }
        }
    });
    //监听登陆提交
    form.on('submit(add)', function (data) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/admin/picture/save",
            data: data.field,
            success: function(ret){
                console.log(ret);
                if(ret.isOk){
                    layer.msg("操作成功", {time: 2000},function(){
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index);
                        window.parent.location.href="/admin/picture/index";
                    });
                }else{
                    layer.msg(ret.msg, {time: 2000});
                }
            }
        });
        return false;
    });
    exports('picture/form', {});
});

