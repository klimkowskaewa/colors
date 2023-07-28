package com.example.colors.controller;

import com.example.colors.dao.SelectedColorDao;
import com.example.colors.model.SelectedColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

@Controller
public class SelectedColorController {

    @Value("${app.title.select}")
    private String title;

    @Autowired
    SelectedColorDao selectedColorDao;

    @GetMapping({"/select", "/select/{color}"})
    public String selectedColorPage(@PathVariable(required = false) String color, Model model){

        if (!StringUtils.isEmpty(color)){
            selectedColorDao.save(new SelectedColor(color, new Date()));
        }

        String[][] colors = {
                {"red", "blue", "purple", "teal"},
                {"black","orange", "yellow", "green"},
                {"gray","silver", "olive", "lime"},
                {"navy","white", "aqua", "fuchsia"}
        };

        model.addAttribute("title", title);
        model.addAttribute("colors", colors);
        return "select";
    }
}