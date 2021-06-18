package com.leyou.page.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Felix
 * @Description:
 */
@Controller
public class PageController {
    @GetMapping("item/{id}.html")
    public String toItemPage(Model model, @PathVariable("id")Long id) {
        return "item";
    }
}
