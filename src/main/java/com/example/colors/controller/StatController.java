package com.example.colors.controller;

import com.example.colors.helper.ColorHelper;
import com.example.colors.model.SelectedColor;
import com.example.colors.service.SelectedColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StatController {

    @Value("${app.title.stat}")
    private String title;

    @Autowired
    SelectedColorService selectedColorService;
    
    @GetMapping("/stat")
    String statPage(Model model) {
        List<SelectedColor> selectedColors = selectedColorService.getAllData();
        Map<String, Long> dataMap = selectedColors.stream().collect(Collectors.groupingBy(SelectedColor::getColor, Collectors.counting()));

        model.addAttribute("title", title);
        model.addAttribute("labels", dataMap.keySet());
        model.addAttribute("data", dataMap.values());
        model.addAttribute("backgrounds", ColorHelper.convertColors(dataMap.keySet()));

        return "stat";
    }
}
