layui.define(['laypage', 'layer',  'table','common','util','form'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        util = layui.util,
        laypage = layui.laypage,
        form = layui.form,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#major'
        ,height: 'full-200'
        ,method:'GET'
        ,url: '/admin/picture/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {type: 'checkbox', align:'center',unresize:true}
            ,{field: 'productId', align:'center', title: '产品ID', sort: true,unresize:true}
            ,{field: 'pictureType', align:'center', title: '图片类型',sort: true,unresize:true}
            ,{field: 'pictureSize', align:'center', title: '图片尺寸',unresize:true}
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


    //添加数据
    $('#addMajor').click(function () {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            common.frame_show('分类添加','/admin/major/form');
            // layer.msg('打开添加窗口');
        }, 500);
    });

    //批量删除数据
    $('#deleteAll').click(function () {
        var index = layer.load(1);

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
                url: "/admin/major/" + id + "/del",
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/admin/major/index";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }

    function Format(datetime,fmt) {
        if (parseInt(datetime)==datetime) {
            if (datetime.length==10) {
                datetime=parseInt(datetime)*1000;
            } else if(datetime.length==13) {
                datetime=parseInt(datetime);
            }
        }
        datetime=new Date(datetime);
        var o = {
            "M+" : datetime.getMonth()+1,                 //月份
            "d+" : datetime.getDate(),                    //日
            "h+" : datetime.getHours(),                   //小时
            "m+" : datetime.getMinutes(),                 //分
            "s+" : datetime.getSeconds(),                 //秒
            "q+" : Math.floor((datetime.getMonth()+3)/3), //季度
            "S"  : datetime.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (datetime.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }


    exports('picture/index', datalist);
});