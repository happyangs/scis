package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.Type;
import com.jcohy.scis.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 23:54 2018/2/6
 * Email: jia_chao23@126.com
 * ClassName: AdminTypeController
 * Description:
 **/
@Controller
@RequestMapping("/admin/type")
public class AdminTypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/list")
    @ResponseBody
    public PageJson<Type> all(ModelMap map){
        PageJson<Type> page = new PageJson<>();
        return page;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, ModelMap map){
        return "admin/type/form";
    }

    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(Type type){
        return JsonResult.ok();
    }

    @DeleteMapping("{id}/del")
    @ResponseBody
    public JsonResult del(@PathVariable("id") Integer id){
        return JsonResult.ok();
    }

}
