package org.paleha.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Аннотация - контроллер
public class AboutController {
    @GetMapping("/about") // под-страница about
    public String about(Model model) {
        model.addAttribute("title", "О нас"); // Передаем параметры, которые будут применены к шаблону
        return "about"; // Теперь возвращает шаблон со страницей О нас!
    }
}
