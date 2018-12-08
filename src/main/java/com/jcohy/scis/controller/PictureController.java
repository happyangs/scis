package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.DeptService;
import com.jcohy.scis.service.MajorService;
import com.jcohy.scis.service.PictureService;
import com.jcohy.scis.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiac on 2018/5/25.
 * ClassName  : com.jcohy.scis.controller
 * Description  :
 */
@Controller
@RequestMapping("/admin/picture")
public class PictureController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PictureService pictureService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    @ResponseBody
    public PageResponse<BkProductPictureVo> all(@RequestParam Integer page,
                                                @RequestParam Integer limit,
                                                @RequestParam(required = false) Integer productId){
        BkProductPictureReq bkProductPictureReq = new BkProductPictureReq(limit,page);
        PageResponse pageResponse = pictureService.queryByCondition(bkProductPictureReq);
        return pageResponse;
    }

    /**
     *
     * @param id
     * @param pid 待添加图片产品
     * @param map
     * @return
     */
    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id,
                       @RequestParam(required = false) Integer pid,
                       ModelMap map){
        BkProductPictureVo bkProductPictureVo = new BkProductPictureVo();
        if (pid != null){// 添加图片
            bkProductPictureVo.setProductId(pid);
        }else{
            bkProductPictureVo = pictureService.queryById(id);
        }
        map.put("picture",bkProductPictureVo);
        return "admin/picture/form";
    }

    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(BkProductPictureReq bkProductPictureVo){
        return JsonResult.ok();
    }

    @DeleteMapping("{id}/del")
    @ResponseBody
    public JsonResult del(@PathVariable("id") Integer id){
        try {
            majorService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
        return JsonResult.ok();
    }

//    @GetMapping("/{id}/change")
//    @ResponseBody
//    public JsonResult change(@PathVariable("id") Long id, String type){
//        try {
//            deptService.change(id,type);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return JsonResult.fail(e.getMessage());
//        }
//        return JsonResult.ok("操作成功");
//    }
}
