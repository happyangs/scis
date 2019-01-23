layui.define(['laypage', 'layer',  'table','common','util','form'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        util = layui.util,
        laypage = layui.laypage,
        form = layui.form,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#picture'
        ,height: 'full-200'
        ,method:'GET'
        ,url: '/admin/picture/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {type: 'checkbox', align:'center',unresize:true}
            ,{field: 'productId', align:'center', title: '产品ID', sort: true,unresize:true}
            ,{field: 'pictureTypeName', align:'center', title: '图片类型',sort: true,unresize:true}
            ,{field: 'pictureSizeDesc', align:'center', title: '图片尺寸',unresize:true}
            ,{field: 'picturePath', align:'center', title: '图片链接',sort: true,unresize:true}
            ,{field: 'remark', align:'center', title: '备注',unresize:true}
            ,{field: 'addTime', title: '创建日期',unresize:true}
            ,{field: 'updateTime', title: '更新时间',unresize:true}
            ,{fixed: 'right',  title:'操作',align:'center', toolbar: '#operator',unresize:true}
        ]]
    });
    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            del(data.id);
        } else if(obj.event === 'edit'){
            common.frame_show('分类编辑','/admin/picture/form?id='+data.id);
        }
    });
    //分页
    laypage.render({
        elem: 'pageDemo' //分页容器的id
        ,count: 100 //总页数
        ,skin: '#1E9FFF' //自定义选中色值
        //,skip: true //开启跳页
        ,jump: function(obj, first){
            if(!first){
                layer.msg('第'+ obj.curr +'页');
            }
        }
    });

    $('#search').click(function () {
        var productId = $("#productId").val();
        table.reload('picture', {
            url: "/admin/picture/list"
            ,where: {
                'productId' : productId
            } //设定异步数据接口的额外参数
            // ,where: {keyword:keyword,dept:dept,major:major} //设定异步数据接口的额外参数
        });
    });

    //添加数据
    $('#addPicture').click(function () {
        var index = layer.load(1);
        var pid = $('#productId').val();
        setTimeout(function () {
            layer.close(index);
            common.frame_show('分类添加','/admin/picture/form?pid='+pid);
            // layer.msg('打开添加窗口');
        }, 500);
    });

    //输出接口，主要是两个函数，一个删除一个编辑
    var datalist = {
        deleteData: function (id) {
            layer.confirm('确定删除？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                del(id);
            }, function () {

            });
        },
        editData: function (id) {
            common.frame_show('分类编辑','/admin/major/form?id='+id);
        }
    };
    function del(id) {
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                type: "DELETE",
                dataType: "json",
                url: "/admin/picture/" + id + "/del",
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            $('#search').click()
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }

    exports('picture/index', datalist);

});