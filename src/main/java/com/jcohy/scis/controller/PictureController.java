package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.common.interfaces.ConfigTypeEnum;
import com.jcohy.scis.model.BkConfig;
import com.jcohy.scis.model.BkProductPictureReq;
import com.jcohy.scis.model.BkProductPictureVo;
import com.jcohy.scis.service.PictureService;
import com.jcohy.scis.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/picture")
public class PictureController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PictureService pictureService;

    @Autowired
    private TypeService typeService;


    @GetMapping("/list")
    @ResponseBody
    public PageResponse<BkProductPictureVo> all(@RequestParam Integer page,
                                                @RequestParam Integer limit,
                                                @RequestParam(required = false) Integer productId){
        BkProductPictureReq bkProductPictureReq = new BkProductPictureReq(limit,page);
        bkProductPictureReq.setProductId(productId);
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
        if (id == null){// 添加图片
            bkProductPictureVo.setProductId(pid);
        }else{
            bkProductPictureVo = pictureService.queryById(id);
        }
        List<BkConfig> pictureType = typeService.getBkConfig(ConfigTypeEnum.PICTURE_TYPE.getCode());
        List<BkConfig> pictureSize = typeService.getBkConfig(ConfigTypeEnum.PRODUCT_SIZE.getCode());
        map.put("pictureType",pictureType);
        map.put("pictureSize",pictureSize);
        map.put("picture",bkProductPictureVo);

        return "admin/picture/form";
    }

    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(BkProductPictureReq bkProductPictureReq){
        try {
            pictureService.insertOrUpdate(bkProductPictureReq);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @DeleteMapping("{id}/del")
    @ResponseBody
    public JsonResult del(@PathVariable("id") Integer id){
        try {
            pictureService.delete(id);
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
