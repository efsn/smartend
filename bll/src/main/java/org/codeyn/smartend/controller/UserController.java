package org.codeyn.smartend.controller;

import javax.servlet.http.HttpServletRequest;

import org.codeyn.smartend.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, String username, String password, ModelMap modelMap){
        
        modelMap.put("username", username);
        modelMap.put("password", password);
        
        return jsonSuccess(modelMap);
    }
    
}
