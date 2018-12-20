layui.define([ 'layer',  'table','common'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#order'
        ,method:'GET'
        ,url: '/order/list' //数据接口
        ,height: 655
        ,cols: [[ //表头
            {type: 'checkbox', align:'center',unresize:true}
            ,{field: 'orderId', align:'center', title: '订单号',width:130,unresize:true}
            ,{field: 'productId', align:'center', title: '作品ID',width:80,unresize:true}
            ,{field: 'productName', align:'center', title: '作品名称',width:170,unresize:true}
            ,{field: 'price', align:'center', title: '价格',width:90,unresize:true}
            ,{field: 'productTypeName', align:'center', title: '类型',width:120,unresize:true}
            ,{field: 'buyerSchool', align:'center', title: '学校',width:180,unresize:true}
            ,{field: 'buyerEmail', align:'center',width:180, title: '邮箱',unresize:true}
            ,{field: 'orderStatusName', align:'center', title: '状态',width:70,unresize:true}
            // ,{field: 'sendTime', align:'center', title: '发送时间',width:175,unresize:true}
            // ,{field: 'remarks', align:'center', title: '备注',width:70,unresize:true}
            ,{field: 'salesMan', align:'center', title: '销售员',width:90,unresize:true}
            ,{field: 'addTime', align:'center', title: '创建时间',width:175,unresize:true,sort: true}
            // ,{field: 'updateTime', align:'center', title: '更新时间',unresize:true}
            ,{fixed: 'right',  title:'操作',width:220,align:'center', toolbar: '#operator',unresize:true}
        ]]
        ,page: true //开启分页
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            del(data.id);
        } else if(obj.event === 'edit'){
            common.frame_show('编辑','/order/form?id='+data.id);
        } else if(obj.event === 'send'){
            send(data.id);
        }
    });

    //添加数据
    $('#addStudent').click(function () {
        layer.msg('功能待开放', {time: 2000});
        // var index = layer.load(1);
        // setTimeout(function () {
        //     layer.close(index);
        //     common.frame_show('分类添加','/admin/student/form');
        //     // layer.msg('打开添加窗口');
        // }, 500);
    });

    var school,productId='';
    $('#search').click(function () {
        school = $("#school").val();
        productId = $("#productId").val();
        table.reload('order', {
            url: "/order/list"
            ,where: {
                'buyerSchool': school,
                'productId' : productId

            }
        });
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
            common.frame_show('分类编辑','/order/form?id='+id);
        }
    };

    function send(id) {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "/order/retry/send?id=" + id,
            success: function (ret) {
                if (ret.isOk) {
                    layer.msg("发送成功", {time: 2000}, function () {
                        layer.close(index);
                        window.location.href = "/admin/order/index";
                    });
                } else {
                    layer.msg(ret.msg, {time: 2000});
                }
            }
        });
    }
    function del(id) {
        // layer.msg('功能待开放', {time: 2000});
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                type: "DELETE",
                dataType: "json",
                url: "/order/" + id + "/del",
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/admin/order/index";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }
    exports('admin/order/index', datalist);
});