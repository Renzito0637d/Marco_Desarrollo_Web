package com.urbanfeet.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("admin/Productos")
    public String getMethodName() {
        return "Admin/Productos";
    }
        
}
