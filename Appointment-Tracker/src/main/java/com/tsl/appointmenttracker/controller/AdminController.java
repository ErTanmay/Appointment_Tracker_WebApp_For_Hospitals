package com.tsl.appointmenttracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("login")
    public String adminLogin(HttpServletRequest request, Model m) {

        Object contextAttribute = request.getServletContext().getAttribute("logout");

        if(contextAttribute instanceof Boolean ){
            m.addAttribute("logout", contextAttribute);
            request.getServletContext().removeAttribute("logout");
        }

        return "/adminLogin";
    }
}
