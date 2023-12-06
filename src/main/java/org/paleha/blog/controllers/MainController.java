package org.paleha.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {
    @GetMapping("/") // косая черта в кавычках означает что функция обрабатывает корневую или главную страницу
    public String home(Model model) {
        model.addAttribute("title", "Главная страница"); // Передаем параметры, которые будут применены к шаблону
        return "home"; // возвращает шаблон домашней страницы
    }

}