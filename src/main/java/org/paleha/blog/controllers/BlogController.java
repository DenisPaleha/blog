package org.paleha.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Аннотация - контроллер
public class BlogController {

    @GetMapping("/blog") // Аннотация определяет какую именно страницу отслеживает контроллер
    public String blogMine(Model model) { // Функция возвращает строку с названием шаблона, который мы должны подключить
        return "info";
    }

}
