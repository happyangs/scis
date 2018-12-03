layui.define([ 'layer',  'table','common'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#product'
        ,method:'GET'
        ,url: '/product/list' //数据接口
        ,height: 655
        ,cols: [[ //表头
            {type: 'checkbox', align:'center',unresize:true}
            ,{field: 'productId', align:'center', title: '产品ID',width:100,unresize:true}
            ,{field: 'productType', align:'center', title: '类型',width:65,unresize:true}
            ,{field: 'productName', align:'center', title: '名称',width:200,unresize:true}
            ,{field: 'price', align:'center', title: '价格',width:100,unresize:true}
            ,{field: 'htmlNum', align:'center', title: '张数',width:65,unresize:true}
            ,{field: 'productDesc', align:'center', title: '描述',width:250,unresize:true}
            ,{field: 'productSynopsis', align:'center', title: '简介',width:250,unresize:true}
            ,{field: 'addTime', align:'center', title: '添加时间',width:175,unresize:true}
           /* ,{field: 'updateTime', align:'center', title: '更新时间',width:175,unresize:true}*/
            ,{field: 'isDelete', align:'center', title: '开关',width:65,unresize:true}
            ,{fixed: 'right',  title:'操作',align:'center', toolbar: '#operator',unresize:true}
        ]]
        ,page: true //开启分页
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            del(data.id);
        } else if(obj.event === 'edit'){
            // common.frame_show('编辑','/admin/student/form?id='+data.id);
            layer.msg('功能待开放', {time: 2000});
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

    //批量删除数据
    $('#deleteAll').click(function () {
        // var index = layer.load(1);
        layer.msg('功能待开放', {time: 2000});
    });

    var school,productId='';
    $('#search').click(function () {
        productId = $("#productId").val();
        table.reload('product', {
            url: "/product/list"
            ,where: {
                'productId' : productId

            } //设定异步数据接口的额外参数
            // ,where: {keyword:keyword,dept:dept,major:major} //设定异步数据接口的额外参数
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
            common.frame_show('分类编辑','/admin/student/form?id='+id);
        }
    };
    function del(id) {
        layer.msg('功能待开放', {time: 2000});
        // layer.confirm('真的删除行么', function (index) {
        //     $.ajax({
        //         type: "DELETE",
        //         dataType: "json",
        //         url: "/admin/student/" + id + "/del",
        //         success: function (ret) {
        //             if (ret.isOk) {
        //                 layer.msg("操作成功", {time: 2000}, function () {
        //                     layer.close(index);
        //                     window.location.href = "/admin/student/index";
        //                 });
        //             } else {
        //                 layer.msg(ret.msg, {time: 2000});
        //             }
        //         }
        //     });
        // });
    }
    exports('admin/order/index', datalist);
});