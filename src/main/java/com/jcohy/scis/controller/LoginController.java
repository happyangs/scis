package com.jcohy.scis.controller;

import com.jcohy.lang.StringUtils;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.model.Admin;
import com.jcohy.scis.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName  : LoginController
 * Description  :登录模块处理
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private AdminService adminService;

    /**
     * 登录处理
     * @param num
     * @param password
     * @param role
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(Long num, String password,
                            @RequestParam(required = false) String role, HttpServletRequest request){
        try {
            if(num == null || StringUtils.isEmpty(password)){
                return JsonResult.fail("用户名或者密码不能为空");
            }
            HttpSession session = request.getSession();
            session.setAttribute("role",role);
            logger.error("name:{}  password:{}  type:{}",num,password,role);
            if(StringUtils.trim(role).equals("admin")){
                Admin login = adminService.login(num, password);
                if(login == null){
                    return JsonResult.fail("登录失败,用户名不存在");
                }
                if(!login.getPassword().equals(password)){
                    return JsonResult.fail("登录失败,用户名账号密码不匹配");
                }
                session.setAttribute("user",login);
                return JsonResult.ok().set("returnUrl", "/admin/main");
            }
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.fail("角色不存在");
    }


    /**
     * 注销用户
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/";
    }


    /**
     * 更新密码
     * @param role
     * @param num
     * @param oldPassword
     * @param newPassword
     * @param rePassword
     * @param map
     * @return
     */
    @PostMapping("/admin/update/")
    @ResponseBody
    public JsonResult updatePassword(@SessionAttribute("role") String role,@RequestParam Long num,@RequestParam String oldPassword, @RequestParam String newPassword,
                                 @RequestParam String rePassword, ModelMap map){
        logger.error("role:{}",role);
        if(StringUtils.isEmpty(role)){
            return JsonResult.fail("此用户不存在");
        }
        if(StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(rePassword)){
            return JsonResult.fail("参数不完整");
        }
        if(!newPassword.equals(rePassword)){
            return JsonResult.fail("两次输入密码不一致");
        }
        if(role.equals("admin")){
            Admin dbUser = adminService.findByNum(num);
            if(!dbUser.getPassword().equals(oldPassword)){
                return JsonResult.fail("旧密码不正确");
            }
            dbUser.setPassword(newPassword);
            adminService.updatePassword(dbUser);
        }
        return JsonResult.ok();
    }


}
