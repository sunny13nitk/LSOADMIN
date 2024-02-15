package com.sap.cds.lsoadmin.UIControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController
{
    @GetMapping("/logout")
    public String showLogout()
    {
        return "logout";
    }

}
