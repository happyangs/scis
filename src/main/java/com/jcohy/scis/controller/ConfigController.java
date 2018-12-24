package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.common.interfaces.ConfigTypeEnum;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.DeptService;
import com.jcohy.scis.service.MajorService;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dell
 */
@Controller
@RequestMapping("/admin/config")
public class ConfigController extends BaseController{

    @Autowired
    private TypeService typeService;

    @GetMapping("/index")
    public String index(ModelMap map){
        BkConfigReq bkConfigReq = new BkConfigReq();
        List<BkConfig> list = typeService.queryConfigByCondition(bkConfigReq);
//        根据两个属性去重
//        List<BkConfig> configType = list.stream().collect(
//                Collectors.collectingAndThen(
//                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getCode() + ";" + o.getZhName()))), ArrayList::new)
//        );

        List<BkConfig> configType = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getConfigType()))), ArrayList::new)
        );
        configType.forEach(bkConfig -> bkConfig.setConfigTypeDesc(ConfigTypeEnum.code2desc(bkConfig.getConfigType())));

//        Map<String,String> configTypeMap = list.stream().distinct().collect(Collectors.toMap(BkConfig::getConfigType,a->ConfigTypeEnum.code2desc(a.getConfigType())));

        map.put("configType",configType);
        return "admin/config/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public PageResponse all(@RequestParam Integer page,
                            @RequestParam Integer limit,
                            @RequestParam(required = false) String configType,
                            @RequestParam(required = false) Integer code){
        BkConfigReq bkConfigReq = new BkConfigReq();
        bkConfigReq.setPageNum(page);
        bkConfigReq.setPageSize(limit);
        bkConfigReq.setConfigType(configType);
        bkConfigReq.setCode(code);
        PageResponse response = typeService.queryConfig(bkConfigReq);
        return response;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, ModelMap map){
        if(id != null){
            BkConfigReq bkConfigReq = new BkConfigReq();
            bkConfigReq.setId(id);
            List<BkConfig> list = typeService.queryConfigByCondition(bkConfigReq);
            if (!CollectionUtils.isEmpty(list)){
                map.put("config",list.get(0));
            }
        }
        return "admin/config/form";
    }

    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(BkConfigReq bkConfigReq){
        try {
            typeService.insertOrUpdate(bkConfigReq);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @DeleteMapping("{id}/del")
    @ResponseBody
    public JsonResult del(@PathVariable("id") Integer id){
        try {
            typeService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
        return JsonResult.ok();
    }
}
