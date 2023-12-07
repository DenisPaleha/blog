package org.paleha.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/") // косая черта в кавычках означает что функция обрабатывает корневую или главную страницу
    public String home(Model model) {
        model.addAttribute("title", "Главная страница"); // Передаем параметры, которые будут применены к шаблону
        return "home"; // возвращает шаблон домашней страницы
    }

//    @PostMapping
//    public String processForm(String userInput) {
//        // userInput содержит данные, введенные пользователем
//        // Вы можете выполнить нужные действия с этими данными здесь
//        return "result"; // возвращает шаблон для отображения результата
//    }

    @PostMapping("/processForm")
    public String processForm(String userInput, Model model) {
        // userInput содержит данные, введенные пользователем.
        // Выполняем обработку данных, например, преобразуем строку.
        String processedInput = userInput.toUpperCase();

        // Добавляем обработанные данные в модель для возврата на страницу
        model.addAttribute("processedInput", processedInput);

        return "home"; // возвращает шаблон домашней страницы (где находится ваша форма)
    }


}