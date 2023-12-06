package org.paleha.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Аннотация должна быть указана - иначе это просто класс!
public class InfoController {
    @GetMapping("/info") // Аннотация определяет какую именно страницу отслеживает контроллер
    public String info(Model model) { // Функция возвращает строку с названием шаблона, который мы должны подключить
        model.addAttribute("title", "Функционал");
        return "info";
    }
}
