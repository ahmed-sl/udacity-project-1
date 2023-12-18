package com.SuperCloudStorge.Controller;


import com.SuperCloudStorge.Model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String loginPage(@ModelAttribute("User_model") UserModel userModel , Model model) {
        return "login";
    }
}
